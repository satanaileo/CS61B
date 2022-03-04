package hashmap;

import edu.neu.ccs.gui.TableLayout;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author satanaileo
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int initialSize = 16;
    private double maxLoad = 0.75;
    private Set<K> keys = new HashSet<>();
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this.buckets = createTable(initialSize);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        this.buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[])new Collection[tableSize];
        for (int i = 0; i < tableSize; i += 1) {
            table[i] = createBucket();
        }
        return table;
    }

    private void resize() {
        Collection<Node>[] newBuckets = createTable(2 * buckets.length);
        for (Collection<Node> bucket : buckets) {
            for (Node nd : bucket) {
                int index = Math.floorMod(nd.key.hashCode(), newBuckets.length);
                newBuckets[index].add(nd);
            }
        }
        buckets = newBuckets;
    }


    @Override
    public void clear() {
        keys.clear();
        for (Collection<Node> bucket : buckets) {
            bucket.clear();
        }
        buckets = createTable(initialSize);
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        Node tmp = getNode(key);
        if (tmp != null) {
            return tmp.value;
        }
        return null;
    }

    private Node getNode(K key) {
        if (containsKey(key)){
            int index = Math.floorMod(key.hashCode(), buckets.length);
            for (Node nd : buckets[index]) {
                if (nd.key.equals(key)) {
                    return nd;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void put(K key, V value) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        boolean added = false;
        for (Node nd : buckets[index]) {
            if (nd.key.equals(key)) {
                nd.value = value;
                added = true;
                break;
            }
        }
        if (!added) {
            buckets[index].add(createNode(key, value));
            keys.add(key);
            if ((double) size() / buckets.length > maxLoad) {
                resize();
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return this.keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIter();
    }

    private class HashMapIter implements Iterator<K> {
        private Iterator<K> iter = keys.iterator();
        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next();
        }
    }
}
