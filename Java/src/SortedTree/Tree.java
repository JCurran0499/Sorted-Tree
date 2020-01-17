/*
 * John Curran
 * 
 * This class defines the methods used in the tree data structure. The empty
 * tree and value tree classes will inherent this parent class. Descriptions
 * of each method can be found in those classes
 * 
 * The tree data structure is a polymorphic binary search tree of generic keys 
 * and a generic value for each key. Keys cannot be duplicated but values can.
 * Keys must extend Comparable because the compareTo() method is used
 * when searching through the tree, which is organized by key 
 */

package SortedTree;

public abstract class Tree<K extends Comparable<K>, V> {	
	public abstract Tree<K, V> addValue(K k, V v) throws TreeException;
	public abstract V getValue(K k) throws TreeException;
	public abstract Tree<K, V> remove(K k) throws TreeException;	
	public abstract int size();
	public abstract boolean keyExists(K k) throws TreeException;
	public abstract void toLinkedList(LinkedList<V> array) throws TreeException, ListException;
	
	protected abstract ValueTree<K, V> tree() throws TreeException;
	protected abstract void addToList(LinkedList<V> array) throws ListException;
}
