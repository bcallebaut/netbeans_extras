/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.palette;

import be.belgiplast.utilities.namespaces.Name;
import be.belgiplast.utilities.namespaces.Namespace;
import be.belgiplast.utilities.relationships.EntityType;
import be.belgiplast.utilities.util.Decorated;
import be.belgiplast.utilities.util.Decorator;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.Action;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.DragAndDropHandler;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.ExTransferable;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author benoit
 */
public class PaletteSupport {
    public static <D> D getDecorator(Object namespace, Class<D> c) {
        D provider = null;
        if (namespace instanceof PaletteProvider){
            provider = (D)namespace;
        }else if (namespace instanceof Decorated){
            //TODO: Test the following statement
            if (Decorated.class.isAssignableFrom(c)){
                provider = (D)((Decorated)namespace).getDecorator((Class<Decorator>)c);
            }
        }
        if (provider == null){
            if (namespace instanceof Lookup.Provider){
                provider = ((Lookup.Provider)namespace).getLookup().lookup(c);
            }
        }
        return provider;
    }
    
    public static PaletteController createPalette(Namespace namespace){        
        AbstractNode paletteRoot = new AbstractNode(new PaletteChildren(namespace));
        paletteRoot.setName("Palette Root");
        return PaletteFactory.createPalette( paletteRoot, new MyActions(), null, new MyDnDHandler() );
    }
    
    private static class PaletteChildren extends Children.Keys<Namespace>{
        private Namespace namespace;

        public PaletteChildren(Namespace namespace) {
            this.namespace = namespace;
        }

        @Override
        protected void addNotify() {
            ArrayList<Namespace> nodes = new ArrayList<>();
            nodes.add(new DefaultCategoryNamespace(namespace));
            nodes.addAll(namespace.getNamespaces());
            setKeys(nodes);
        }

        @Override
        protected Node[] createNodes(Namespace key) {
            return new Node[]{new CategoryNode(key)};
        }
    }
    
    private static class CategoryNode extends AbstractNode{
        private Namespace ns;

        public CategoryNode(Namespace ns) {
            super(new CategoryChildren(ns));
            this.ns = ns;
            setName(ns.getName());
            setDisplayName(ns.getName());
        }
    }
    
    private static class CategoryChildren extends Children.Keys<Name>{

        private Namespace namespace;
        private PaletteProvider provider = null;            
        
        public CategoryChildren(Namespace namespace) {
            this.namespace = namespace;
        }

        @Override
        protected void addNotify() {
            provider = getDecorator(namespace,PaletteProvider.class);
            ArrayList<EntityType> entities = new ArrayList<>();
            for (Name n : namespace.getNames()){
                if (n instanceof EntityType){
                    entities.add((EntityType)n);
                }
            }
            setKeys(entities);
        }

        @Override
        protected Node[] createNodes(Name key) {            
            if (provider != null){
                return new Node[]{new CategoryItemNode(key,provider)};
            }else return new Node[]{new CategoryItemNode(key)};
            
        }
    }
    
    private static class CategoryItemNode extends AbstractNode{
        private Name entityType;
        private PaletteProvider provider;

        public CategoryItemNode(Name entityType) {
            super(Children.LEAF);
            this.entityType = entityType;
            setName(entityType.getName());
            setDisplayName(entityType.getName());
        }

        public Name getType() {
            return entityType;
        }
        
        public CategoryItemNode(Name entityType, PaletteProvider provider) {
            super(Children.LEAF, Lookups.singleton(entityType));
            this.entityType = entityType;            
            setName(entityType.getName());
            setDisplayName(provider.getDisplayName(entityType));
        }

        @Override
        public NewType[] getNewTypes() {
            if (provider != null){
                return provider.getNewTypes(entityType);
            }
            return super.getNewTypes(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Image getIcon(int type) {
            if (provider != null){
                return provider.getIcon(entityType);
            }
            return super.getIcon(type); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Action[] getActions(boolean context) {
            if (provider != null){
                return provider.getActions(entityType);
            }
            return super.getActions(context); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
    private static class MyActions extends PaletteActions {
        public Action[] getImportActions() {
            return null;
        }
        
        public Action[] getCustomPaletteActions() {
            return null;
        }
        
        public Action[] getCustomCategoryActions(Lookup lookup) {
            return null;
        }
        
        public Action[] getCustomItemActions(Lookup lookup) {
            return null;
        }
        
        public Action getPreferredAction(Lookup lookup) {
            return null;
        }
        
    }
    
    private static class MyDnDHandler extends DragAndDropHandler {

        public void customize(ExTransferable exTransferable, Lookup lookup) {
            Node node = lookup.lookup(Node.class);
            
            try{
                final Name entityType = node.getLookup().lookup(Name.class);                
                exTransferable.put(new ExTransferable.Single (new DataFlavor(Name.class,"name.type")) {
                
                protected Object getData() throws IOException, UnsupportedFlavorException {
                    return entityType;
                }
                
            });
            }catch (Exception e){
                Logger.getAnonymousLogger().warning("Node is not a CategoryItemNode. Unable to DnD");
                exTransferable.put(new ExTransferable.Single (new DataFlavor(Name.class,"name.type")) {

                    protected Object getData() throws IOException, UnsupportedFlavorException {
                        return null;
                    }
                });
            }
        }        
    }
}
