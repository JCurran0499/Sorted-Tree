/*
 * John Curran
 * 
 * This is the value tree, which contains nodes with a key and value for each.
 * Every tree is made up with branches of value trees that each end with an
 * empty tree. Most methods are recursive, which is where the empty tree comes in
 */

package SortedTree;

@SuppressWarnings("unchecked")
class ValueTree<K extends Comparable<K>, V> extends Tree<K, V> {
	
	//every value tree node has a key, a value, and a left and right node
	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;
	
	//set the value and key, and then set each node to the empty tree instance
	public ValueTree(K k, V v) throws TreeException {
		if (k == null || v == null)
			throw new TreeException("Invalid Key or Value");
		
		key = k;
		value = v;
		left = EmptyTree.emptyTree();
		right = EmptyTree.emptyTree();
	}	
	
	//this method adds a node to the tree with the given key and value.
	//For it to work properly every time, the user must write
	//tree = tree.addValue(k, v) instead of just tree.addValue(k, v)
	public Tree<K, V> addValue(K k, V v) throws TreeException {
		if (k == null || v == null || keyExists(k))
			throw new TreeException("Invalid Key or Value");
		
		//recursively travel through the tree, going left or right depending
		//on the key, and then add it to the end of the proper branch.
		//The recursive process ends once an empty tree has been reached
		if (key.compareTo(k) != 0) {
			if (key.compareTo(k) < 0)
				right = right.addValue(k, v);		
			else 
				left = left.addValue(k, v);
		}
		
		return this;
	}
	
	//this method returns the value of the given key in the tree
	public V getValue(K k) throws TreeException {
		if (k == null)
			throw new TreeException("Invalid Key");
		
		//recursively travel through the tree, returning the value
		//once the key has been found. By the nature of empty trees, an
		//exception will be thrown if this reaches an empty tree, because
		//that means the key does not exist
		if (key.compareTo(k) == 0)
			return value;
		else if (key.compareTo(k) < 0)
			return right.getValue(k);
		else 
			return left.getValue(k);
	}
	
	//this method removes a tree node with the given key. This is done
	//by swapping the node to be removed with the maximum node on the
	//left branch (or the minimum node on the right branch) and then 
	//removing the node in its new spot
	public Tree<K, V> remove(K k) throws TreeException {
		if (k == null)
			throw new TreeException("Invalid Key");
		
		if (key.compareTo(k) == 0 && !hasLeft() && !hasRight())
			return EmptyTree.emptyTree();
		
		ValueTree<K, V> temp = this;
		ValueTree<K, V> tempParent = null;
		//this will set temp to the tree node matching the key,
		//throwing an exception (via tree()) if it isn't found. tempParent
		//will be the parent node of the temp node (this is so removing it
		//at the end will be painless)
		while (temp.key.compareTo(k) != 0) {
            if (temp.key.compareTo(k) < 0) {
            	tempParent = temp;            
                temp = temp.right.tree();
            } else if (temp.key.compareTo(k) > 0) {
            	tempParent = temp;
                temp = temp.left.tree();
            }
        }
		
		//swapTree will be the minimum value on the right branch (or
		//maximum value on the left branch) that will be swapped into
		//the current position of the node that will be removed.
		//The following process will set swapTree and then set tempParent
		//to swapTree's parent
		ValueTree<K, V> swapTree;
		if (temp.hasRight()) {
			tempParent = temp;
			swapTree = temp.right.tree();
			
			while (swapTree.hasLeft()) { //sets to minimum value on the right
				tempParent = swapTree;	
				swapTree = swapTree.left.tree();
			}
			
		} else if (temp.hasLeft()) {
			tempParent = temp;
			swapTree = temp.left.tree();
			
			while (swapTree.hasRight()) { //sets to maximum value on the left
				tempParent = swapTree;
				swapTree = swapTree.right.tree(); 
			}
		} else {
			//if the node has neither a right nor left branch, that means it
			//can be removed immediately without any swapping necessary
			if (tempParent.left == temp)
				tempParent.left = EmptyTree.emptyTree();
			else 
				tempParent.right = EmptyTree.emptyTree();
			
			return this; //early return statement ends the method early
		}
		
		//reset the node to be removed. It will now take the attributes of the
		//swap tree, which is just as effective as actually swapping the two
		temp.key = swapTree.key;
		temp.value = swapTree.value;
		
		//use tempParent to remove swapTree, which is where the removed node
		//would end up if they were actually swapped
		if (tempParent.left == swapTree)
			tempParent.left = EmptyTree.emptyTree();
		else 
			tempParent.right = EmptyTree.emptyTree();
		
		return this;
	}
	
	//returns the size of the tree, meaning the amount of nodes. The recursive
	//process ends once an empty tree is reached 
	public int size() {
		return 1 + left.size() + right.size();
	} 
	
	//returns whether a node of the given key exists within the tree
	public boolean keyExists(K k) throws TreeException {
		if (k == null)
			throw new TreeException("Invalid Key");
		
		//similar process to getValue. Given the empty tree method returns false,
		//this will return false if an empty tree is reached 
		if (key.compareTo(k) == 0)
			return true;
		else if(key.compareTo(k) < 0)
			return right.keyExists(k);
		else 
			return left.keyExists(k);
	}
	
	//this method takes a LinkedList parameter, clears it, and then fills
	//it with every tree value in order by key. This is for simple 
	//organizational and ordering purposes
	public void toLinkedList(LinkedList<V> array) throws TreeException, ListException {
		array.clear();
		addToList(array);
	}
	
	//this method has a very specific use. If the tree calling it is a value
	//tree, it will simply return itself. If it is an empty tree, it will 
	//throw an exception. This is useful in the remove() method, where it will
	//catch if the temp variable is an empty tree. It also allows the 
	//Tree left and right nodes to be assigned to the ValueTree temp variable
	//without any casting necessary
	protected ValueTree<K, V> tree() throws TreeException {
		return this;
	}
	
	//this helper method is used so array.clear() is not called in every 
	//recursive step. It adds the calling node's value to the array, and then 
	//recursively performs this on both the left and right node. This will 
	//carry down until the end of every branch
	protected void addToList(LinkedList<V> array) throws ListException {
		array.addValue(value);
		left.addToList(array);
		right.addToList(array);
	}
	
	//returns whether the current tree has a left node
	private boolean hasLeft() {
		return left.size() > 0;
	}
	
	//returns whether the current tree has a left node
	private boolean hasRight() {
		return right.size() > 0;
	}
}
