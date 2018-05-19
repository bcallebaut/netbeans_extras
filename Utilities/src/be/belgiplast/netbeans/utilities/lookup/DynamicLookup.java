/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.lookup;

import java.util.HashMap;
import java.util.Map;
import org.openide.util.Lookup;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author A595564
 */
public class DynamicLookup extends ProxyLookup{
    private Map<String,Lookup> lookups = new HashMap<>();

    public Lookup put(String key, Lookup value) {
        Lookup lkp = lookups.put(key, value);
        super.setLookups(lookups.values().toArray(new Lookup[lookups.size()]));
        return lkp;
    }

    public Lookup remove(String key) {
        Lookup lkp = lookups.remove(key);
        super.setLookups(lookups.values().toArray(new Lookup[lookups.size()]));
        return lkp;
    }
}
