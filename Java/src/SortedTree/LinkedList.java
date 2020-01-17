/*
 * John Curran
 * 
 * This class acts as a very simple unordered Linked List that stores a
 * list of generic types. Unlike the Tree data structure, this list is 
 * not polymorphic, and is simply terminated with null
 */

package SortedTree;

import java.util.ArrayList;

public class LinkedList<T> {	
	
	//this nested class provides the Node object, which stores a value and
	//another node that acts as the next one in the list
	private class Node {
		private T value;
		private Node next;
		
		private Node(T v) {
			value = v;
			next = null;
		}
	}
	
	//the list contains a head node and the size of the list (to avoid
	//having to go through the entire list to find the size each time)
	private Node head;
	private short size;
	
	//two different constructors are offered. If no arguments are given,
	//it will leave the list with an empty header
	public LinkedList(T v) throws ListException {
		if (v == null)
			throw new ListException("Invalid Value");
		
		head = new Node(v);
		size = 1;
	}
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	//this method's name speaks for itself. It will add the given value
	//to the end of the list
	public void addValue(T v) throws ListException {
		if (v == null)
			throw new ListException("Invalid Value");
		if (head == null)
			head = new Node(v);
		else {
			Node temp = head;
		
			//this loop takes temp to the last node in the list, so the
			//method can the node after that to the new node in the list
			while (temp.next != null)
				temp = temp.next;
		
			temp.next = new Node(v);
		}
		
		size++;
	}
	
	//this method adds a value to the linked list in a specific index 
	//(starting at 0)
	public void addValueAtIndex(T v, int index) throws ListException {
		if (index > size || index < 0)
			throw new ListException("Out of Bounds");
		if (v == null)
			throw new ListException("Invalid Value");

		Node temp = head; //temporary node used for searching the list
		
		//special case if the index is the head. Create a new node for the
		//beginning, and then set the current head to be the second spot, and
		//then set the head property to the new real head (temp)
		if (index == 0) {
			temp = new Node(v);
			temp.next = head;
			head = temp;
		} else {
			//this loop will set temp to the node right before the 
			//given index
			for (int i = 1; i < index; i++)
				temp = temp.next;
				
			//temp2 is used as the new node to be added to the list.
			//Add it to the end of temp, and then add the rest of the list
			//to the end of it. This will effectively insert it right into 
			//the list 
			Node temp2 = new Node(v);
			temp2.next = temp.next;
			temp.next = temp2;				
		}
		
		size++;
	}
	
	//this method returns the value at the given index
	public T getValue(int index) throws ListException {
		if (index > size - 1 || index < 0)
			throw new ListException("Out of Bounds");
		
		//use the loop to set temp to the node at the given index, 
		//then return its value
		Node temp = head;
		for(int i = 0; i < index; i++)
			temp = temp.next;
		
		return temp.value;
	}
	
	//this method removes the value at the given index
	public void removeValue(int index) throws ListException {
		if (index > size - 1 || index < 0)
			throw new ListException("Out of Bounds");
		
		//when removing the first node, just move the head up one
		if (index == 0)
			head = head.next;
		else {
			Node temp = head;
			//loop up to the node right before the given index
			for (int i = 1; i < index; i++)
				temp = temp.next;
			
			//skip over the next node, effectively removing it from the list
			temp.next = temp.next.next;
		}
		
		size--;
	}
	
	//returns whether the value exists anywhere in the list
	public boolean valueExists(T v) throws ListException {
		if (v == null)
			throw new ListException("Invalid Value");
		
		Node temp = head;	
		//loop through the entire list, returning true once the value is found
		while (temp != null) {
			if (temp.value.equals(v))
				return true;
			
			temp = temp.next;
		}
		
		return false; //reaching here means the value was never found
	}
	
	//returns the size of the list
	public int size() {
		return size;
	}
	
	//clears the entire list by setting the head to null
	public void clear() {
		head = null;
		size = 0;
	}
	
	//this method turns the linked list into an ArrayList. This could be useful
	//if an ArrayList is more appropriate for the users needs
	public ArrayList<T> toArray() {
		Node temp = head;
		ArrayList<T> array = new ArrayList<T>();
		//loop through the entire list, adding each item to the array, 
		//and then return the array
		while (temp != null) {
			array.add(temp.value);
			temp = temp.next;
		}
		
		return array;
	}
}
