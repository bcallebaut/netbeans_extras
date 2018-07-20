/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.nodes;

import be.belgiplast.utilities.relationships.Entity;
import be.belgiplast.utilities.relationships.Relationship;
import org.openide.nodes.Children.Keys;
import org.openide.nodes.Node;

/**
 *
 * @author benoit
 */
public class EntityNode extends NameNode<Entity>{
    
    public EntityNode(Entity entity) {
        super(entity,new EntityChildren(entity));
    }
    
    private static class EntityChildren extends Keys<Relationship>{

        private Entity entity;

        public EntityChildren(Entity entity) {
            this.entity = entity;
        }
        
        @Override
        protected Node[] createNodes(Relationship key) {
            return new Node[]{new RelationshipNode(key)};
        }
        
    }
}
