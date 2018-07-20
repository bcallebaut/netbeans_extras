/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.nodes;


import be.belgiplast.utilities.namespaces.Name;
import be.belgiplast.utilities.namespaces.NamedItem;
import be.belgiplast.utilities.namespaces.Namespace;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children.Keys;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;

/**
 *
 * @author benoit
 */
public class NamespaceNode<NM extends Namespace> extends AbstractNode{
    private NM namespace;
    
    public NamespaceNode(NM entity) {
        super(new EntityChildren(entity));
        this.namespace = entity;
        setName(entity.getName());
    }
    
    protected NamespaceNode(NM entity,EntityChildren<NM> children) {
        super(children);
        this.namespace = entity;
    }
    
    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        
        set.put(new PropertySupport.Name(this));
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    protected static class EntityChildren<NM extends Namespace> extends Keys<NamedItem>{

        private NM entity;

        public EntityChildren(NM entity) {
            this.entity = entity;
        }
        
        @Override
        protected Node[] createNodes(NamedItem key) {
            if (key instanceof Namespace){
                return new Node[]{new NamespaceNode((Namespace)key)};
            }
            if (key instanceof Name){
                return new Node[]{new NameNode((Name)key)};
            }
            return new Node[]{};
        }
        
    }
}
