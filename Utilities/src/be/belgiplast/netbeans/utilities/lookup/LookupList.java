/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author A595564
 */
public class LookupList<E extends Object> implements List<E>,Lookup.Provider{
    private List<E> backend;
    private InstanceContent content;
    private Lookup lookup;

    public LookupList(List<E> backend) {
        this.backend = backend;
        content = new InstanceContent();
        lookup = new AbstractLookup(content);
    }

    public LookupList() {
        this(new ArrayList<E>());
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
    public boolean contains(Object o) {
        return backend.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return backend.iterator();
    }

    @Override
    public Object[] toArray() {
        return backend.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return backend.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean result = backend.add(e);
        content.add(e);
        return result;
    }

    @Override
    public boolean remove(Object o) {
         boolean result = backend.remove(o);
         content.remove(o);
         return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return backend.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E item : c)
            content.add(item);
        return backend.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        for (E item : c)
            content.add(item);
        return backend.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object item : c)
            content.remove(item);
        return backend.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return backend.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        backend.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        backend.sort(c);
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
    public E get(int index) {
        return backend.get(index);
    }

    @Override
    public E set(int index, E element) {
        try{
            content.remove(get(index));
        }catch (Exception e){}
        content.add(element);
        return backend.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        content.add(element);
        backend.add(index, element);
    }

    @Override
    public E remove(int index) {
        content.remove(backend.get(index));
        return backend.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return backend.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return backend.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return backend.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return backend.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return backend.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<E> spliterator() {
        return backend.spliterator();
    }
}
