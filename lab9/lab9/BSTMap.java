package lab9;

import java.security.Key;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) == 0) {
            return p.value;
        }
        else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        }
        else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
       if (p == null) {
           return new Node(key, value);
       }
       if (p.key.compareTo(key) >= 0) {
           p.left = putHelper(key, value, p.left);
       }else {
           p.right = putHelper(key, value, p.right);
       }
       return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value , root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private Set<K> keyset = new HashSet<>();
    @Override
    public Set<K> keySet() {
        InOrder(root, keyset);
        return keyset;
    }
    private void InOrder (Node p, Set<K> keyset) {
        keyset.add(p.key);
        if (p.left != null) {
            InOrder(p.left, keyset);
        }
        if (p.right != null) {
            InOrder(p.right, keyset);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    public V min() {
        return min(root).value;
    }
    public Node min(Node p) {
        if (p.left != null){
            return min(p.left);
        }else {
            return p;
        }
    }
    public V max() {
        return max(root).value;
    }
    public Node max(Node p) {
        if (p.right != null) {
            return max(p.right);
        }else {
            return p;
        }
    }
    public void removeMin() {
        root = removeMin(root);
    }
    private Node removeMin(Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = removeMin(p.left);
        return p;
    }
    public void removeMax(){
        root = removeMax(root);
    }
    private Node removeMax(Node p) {
        if (p.right == null) {
            return p.left;
        }
        p.right = removeMax(p.right);
        return p;
    }
    private Node remove(Node p, K key) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = remove(p.left, key);
        }else if (key.compareTo(p.key) > 0){
            p.right = remove(p.right,key);
        }else {
            if (p.right == null) {
                return p.left;
            }
            if (p.left == null) {
                return p.right;
            }
            Node t = p;
            p = min(p.right);
            p.right = removeMin(p.right);
            p.left = t.left;
        }
        return p;
    }
    @Override
    public V remove(K key) {
        V t = get(key);
        root = remove(root, key);
        size--;
        return t;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
       if (get(key) != value) {
           return null;
       }
       return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keyset.iterator();
    }
}
