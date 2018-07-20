/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.nodes;


import be.belgiplast.utilities.namespaces.Name;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;

/**
 *
 * @author benoit
 */
public class NameNode<NM extends Name> extends AbstractNode{
    private NM namespace;
    
    public NameNode(NM entity) {
        super(Children.LEAF);
        this.namespace = entity;
        setName(entity.getName());
    }
    
    protected NameNode(NM entity,Children children) {
        super(children);
        this.namespace = entity;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        
        set.put(new PropertySupport.Name(this));
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
