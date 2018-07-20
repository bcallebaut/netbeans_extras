/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.support;

import be.belgiplast.netbeans.utilities.relationships.internal.LookupMap;
import be.belgiplast.utilities.namespaces.Name;
import be.belgiplast.utilities.namespaces.NamedItem;
import be.belgiplast.utilities.namespaces.Namespace;
import be.belgiplast.utilities.namespaces.support.MutableNamespace;
import be.belgiplast.utilities.util.JoinMap;
import java.util.ArrayList;
import java.util.Collection;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author benoit
 */
public class AbstractNamespaceSupport implements MutableNamespace,Lookup.Provider{
    private Lookup lookup;
    private String name;
    private Namespace parent;
    private JoinMap<String,NamedItem> items = new JoinMap<>();
    private LookupMap<String,Name> names = new LookupMap<>();
    private LookupMap<String,Namespace> namespaces = new LookupMap<>();

    public AbstractNamespaceSupport() {
        items.addMap(names);
        items.addMap(namespaces);
        lookup = new ProxyLookup(names.getLookup(),namespaces.getLookup(),Lookups.singleton(this));
    }

    public AbstractNamespaceSupport(String name, Namespace parent) {
        this.name = name;
        this.parent = parent;
        items.addMap(names);
        items.addMap(namespaces);
        lookup = new ProxyLookup(names.getLookup(),namespaces.getLookup(),Lookups.singleton(this));
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Namespace getParent() {
        return parent;
    }
    
    @Override
    public void addName(Name name){
        names.put(name.getName(), name);
    }
    
    @Override
    public void addNamespace(Namespace name){
        namespaces.put(name.getName(), name);
    }
    
    @Override
    public void removeName(Name name){
        names.remove(name.getName());
    }
    
    @Override
    public void removeNamespace(Namespace name){
        names.remove(name.getName());
    }

    @Override
    public Collection<NamedItem> getChildren() {
        return items.values();
    }

    @Override
    public Collection<Namespace> getNamespaces() {
        return namespaces.values();
    }

    @Override
    public Collection<? extends Name> getNames() {
        ArrayList<Name> ns = new ArrayList<>();
        for (NamedItem ni : items.values())
            if (ni instanceof Name) ns.add((Name)ni);
        return ns;
    }

    @Override
    public <E extends NamedItem> E findByName(String name) {
        return (E)items.get(name);
    }
    
    @Override
    public Name findNameByName(String name) {
        return names.get(name);
    }
    
    @Override
    public Namespace findNamespaceByName(String name) {
        return namespaces.get(name);
    }
}
