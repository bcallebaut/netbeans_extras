/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.palette;

import be.belgiplast.netbeans.utilities.relationships.support.AbstractNamespaceSupport;
import be.belgiplast.utilities.namespaces.Name;
import be.belgiplast.utilities.namespaces.Namespace;
import java.awt.Image;
import javax.swing.Action;
import org.openide.util.datatransfer.NewType;

/**
 *
 * @author benoit
 */
public class DefaultCategoryNamespace extends AbstractNamespaceSupport implements Namespace,PaletteProvider{
    private Namespace backend;
    private PaletteProvider provider;

    public DefaultCategoryNamespace(Namespace backend) {
        this.backend = backend;
    }

    @Override
    public String getName() {
        return "default";
    }
    
    @Override
    public String getDisplayName(Name type) {
        if (provider == null){
            return "";
        }
        return provider.getDisplayName(type);
    }

    @Override
    public Image getIcon(Name type) {
        if (provider == null){
            return null;
        }
        return provider.getIcon(type);
    }

    @Override
    public Action[] getActions(Name type) {
        if (provider == null){
            return new Action[0];
        }
        return provider.getActions(type);
    }

    @Override
    public NewType[] getNewTypes(Name type) {
        if (provider == null){
            return new NewType[0];
        }
        return provider.getNewTypes(type);
    }
    
    
}
