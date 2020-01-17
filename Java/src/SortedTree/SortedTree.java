/*
 * John Curran
 * 
 * This is the primary class, with the purpose of all other classes being
 * to build this. While this is the most important class, the methods
 * are fairly simple because they are all built on more complex methods
 * that were written in the other classes in this project
 * 
 * A Sorted Tree is a binary search tree in which the key is a string
 * and the value is a linked list. The purpose of this is to create 
 * categories or "divisions" in which to store a list of things. The key
 * of the tree acts as the category name, and the value is the list of
 * objects within that category. This is designed to potentialy increase
 * efficiency of searching through an ordered set of things, as the categories
 * are sorted alphabetically within the tree
 */

package SortedTree;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SortedTree<T> {
	
	private Tree<String, LinkedList<T>> sortedTree;
	
	//there are 3 different constructor options. You can set the sorted
	//tree as empty, with one category, or with multiple categories
	public SortedTree() {
		sortedTree = EmptyTree.emptyTree();
	}
	
	public SortedTree(String division) throws TreeException {
		if (division.equals(""))
			throw new TreeException("Invalid Division");
		
		sortedTree = new ValueTree<String, LinkedList<T>>(division, new LinkedList<T>());
	}
	
	public SortedTree(String[] divisions) throws TreeException {
		sortedTree = EmptyTree.emptyTree();
		for (String s : divisions) {
			if (s == null || s.equals(""))
				throw new TreeException("Invalid Division");
			
			sortedTree = sortedTree.addValue(s, new LinkedList<T>());
		}
	}
	
	
	//adds a division (category) to the sorted tree
	public void addDivision(String division) throws TreeException {
		if (division == null || division.equals(""))
			throw new TreeException("Invalid Division");
		
		sortedTree = sortedTree.addValue(division, new LinkedList<T>());
	}
	
	//returns the number of divisions in the tree
	public int numDivisions() {
		return sortedTree.size();
	}
	
	//returns the number of values in the given division
	public int size(String division) throws TreeException {
		return sortedTree.getValue(division).size();
	}
	
	//returns the total number of values among every division
	public int size() throws TreeException, ListException {
		//sets array to a linked list containing all of the linked lists
		//of the sorted tree
		LinkedList<LinkedList<T>> array = new LinkedList<LinkedList<T>>();
		sortedTree.toLinkedList(array);
		int size = 0;
		
		//add the size of every list to "size", and then return it
		for(int i = 0; i < array.size(); i++) 
			size += array.getValue(i).size();
		
		return size;
	}
	
	//adds a value to a certain division
	public void addValue(String division, T value) throws TreeException, ListException {
		sortedTree.getValue(division).addValue(value);
	}
	
	//adds a value in a certain index within a given division 
	public void addValueAtIndex(String division, T value, int index) throws TreeException, ListException {
		sortedTree.getValue(division).addValueAtIndex(value, index);
	}
	
	//gets the value at a certain index within a given division 
	public T getValue(String division, int index) throws TreeException, ListException {
		return sortedTree.getValue(division).getValue(index);
	}
	
	//returns whether a division exists within the sorted tree
	public boolean divisionExists(String division) throws TreeException{
		return sortedTree.keyExists(division);
	}
	
	//returns whether a value exists within a given division 
	public boolean valueExists(String division, T value) throws TreeException, ListException {
		if (!sortedTree.keyExists(division))
			throw new TreeException("Invalid Division");
		
		return sortedTree.getValue(division).valueExists(value);
	}
	
	//removes an entire division and all of its contents
	public void removeDivision(String division) throws TreeException {
		sortedTree = sortedTree.remove(division);
	}
	
	//removes a value within a division 
	public void removeValue(String division, int index) throws TreeException, ListException {
		sortedTree.getValue(division).removeValue(index);
	}
	
	//clears the entire sorted tree by setting it to empty
	public void clear() {
		sortedTree = EmptyTree.emptyTree();
	}
	
	//turns the contens of a given division into an ArrayList
	public ArrayList<T> toArrayList(String division) throws TreeException {
		return sortedTree.getValue(division).toArray();
	}
}
