/*
 * John Curran
 * 
 * This is the second part of the polymorphic binary search tree, the 
 * empty tree. The empty tree contains no keys or values, and just serves as 
 * the end point of a branch in the overall tree. The main purpose of this
 * class is for certain cases in the more complex methods in the value tree
 * class. Because every branch of a tree ends with an empty tree, there must
 * be ways to handle when a value tree method reaches an empty tree. In other
 * words, these methods only serve to assist in value tree methods
 */

package SortedTree;

@SuppressWarnings("rawtypes")
class EmptyTree<K extends Comparable<K>, V> extends Tree<K, V>{
	
	// this is implemented with the singleton design pattern, meaning there
	// can only exist one instance of this class. The constructor is private
	// (so no new objects of this class can be made), and there is a public
	// method for accessing the one static instance
	private static EmptyTree emptytree = new EmptyTree();
	
	public static EmptyTree emptyTree() {
		return emptytree;
	}
	
	private EmptyTree() {}
	
	//adding a value to an empty tree will simply create a new value tree
	public Tree<K, V> addValue(K k, V v) throws TreeException {
		return new ValueTree<K, V>(k, v);
	}
	
	//if this method ever reaches an empty tree, the key must not exist
	public V getValue(K k) throws TreeException {
		throw new TreeException("Key Does Not Exist");
	}
	
	//if this method ever reaches an empty tree, the key must not exist
	public Tree<K, V> remove(K k) throws TreeException{
		return this;
	}
	
	public int size() {
		return 0;
	}
	
	//if this method ever reaches an empty tree, the key must not exist
	public boolean keyExists(K k) {
		return false;
	}
	
	//this method adds every value of the tree to the given LinkedList, 
	//clearing it first
	public void toLinkedList(LinkedList<V> array) throws TreeException {
		array.clear();
	}
	
	//this helper method is used for the temporary trees in the remove
	//method of the value tree. A more thorough description can be found 
	//in that class's method, but in short this returns the tree calling it
	//if it's a value tree, and throws an exception if it is an empty tree
	protected ValueTree<K, V> tree() throws TreeException {
		throw new TreeException("Key Does Not Exist");
	}
	
	//this is the helper method for adding the values of the tree to the 
	//LinkedList. Once an empty tree has been reached, the recursion stops.
	//In this case, it adds nothing to the list, and thus does nothing at all
	protected void addToList(LinkedList<V> array) {}
}
