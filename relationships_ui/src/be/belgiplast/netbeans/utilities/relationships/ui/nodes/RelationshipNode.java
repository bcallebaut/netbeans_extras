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
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author benoit
 */
public class RelationshipNode extends NameNode<Relationship>{
    
    public RelationshipNode(Relationship entity) {
        super(entity,new EntityChildren(entity));
    }
    
    private static class EntityChildren extends Keys<Entity>{

        private Relationship entity;
        private Lookup.Result<Entity> result;

        public EntityChildren(Relationship entity) {
            this.entity = entity;
        }
        
        @Override
        protected void addNotify() {
            if (entity instanceof Lookup.Provider){
                result = ((Lookup.Provider)entity).getLookup().lookupResult(Entity.class);
                result.addLookupListener(new LookupListener(){
                    @Override
                    public void resultChanged(LookupEvent ev) {
                        setKeys(result.allInstances());
                    }
                });
            }
            
            setKeys(new Entity[] {entity.to()});
        }
        
        @Override
        protected Node[] createNodes(Entity key) {
            return new Node[]{new EntityNode(key)};
        }
        
    }
}
