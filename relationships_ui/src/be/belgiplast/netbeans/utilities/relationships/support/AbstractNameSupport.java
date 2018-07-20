/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.support;

import be.belgiplast.utilities.namespaces.Name;
import be.belgiplast.utilities.namespaces.Namespace;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author benoit
 */
public class AbstractNameSupport implements Name,Lookup.Provider{
    private String name;
    private Namespace parent;
    private Lookup lookup;

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    public AbstractNameSupport(String name) {
        this.name = name;
        lookup = Lookups.singleton(this);
    }

    public AbstractNameSupport(String name, Namespace parent) {
        this.name = name;
        this.parent = parent;
        lookup = Lookups.singleton(this);
    }
    
    public void rename(String name){
        this.name = name;
    }
    
    public final void hook(Namespace parent){
        this.parent = parent;
    }
    
    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Namespace getParent() {
        return parent;
    }
}
