/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author A595564
 */
public class LookupMap<K,V> implements Map<K,V>,Lookup.Provider{
    private Map<K,V> backend;
    private InstanceContent content;
    private Lookup lookup;

    public LookupMap(Map<K,V> backend) {
        this.backend = backend;
        content = new InstanceContent();
        lookup = new AbstractLookup(content);
    }

    public LookupMap() {
        this(new HashMap<K,V>());
    }

    @Override
    public final Lookup getLookup() {
        return lookup;
    }

    @Override
    public int size() {
        return backend.size();
    }

    @Override
    public boolean isEmpty() {
        return backend.isEmpty();
    }

    @Override
    public void clear() {
        backend.clear();
    }

    @Override
    public boolean equals(Object o) {
        return backend.equals(o);
    }

    @Override
    public int hashCode() {
        return backend.hashCode();
    }

    @Override
    public boolean containsKey(Object key) {
        return backend.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return backend.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return backend.get(key);
    }

    @Override
    public V put(K key, V value) {
        V old = backend.put(key, value);
        if (old == null)
            content.add(key);
        return old;
    }

    @Override
    public V remove(Object key) {
        content.remove(key);
        return backend.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        backend.putAll(m);
        for (K key : m.keySet())
            content.add(key);
    }

    @Override
    public Set<K> keySet() {
        return backend.keySet();
    }

    @Override
    public Collection<V> values() {
        return backend.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return backend.entrySet();
    }
}
