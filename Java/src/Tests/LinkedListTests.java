/*
 * John Curran
 * 
 * Tests for the functionality of the LinkedList class
 */

package Tests;

import org.junit.*;
//import static org.junit.Assert.*;
import SortedTree.ListException;
import SortedTree.LinkedList;
//import java.util.ArrayList;

public class LinkedListTests {
	
	//tests creating a basic list with the two constructors
	@Test public void test1() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>(0);
		
		assert(list.size() == 0);
		assert(list2.size() == 1);
	}
	
	//tests basic functionality of addValue()
	@Test public void test2() throws ListException {
		LinkedList<String> list = new LinkedList<String>();
		list.addValue("first");
		list.addValue("second");
		
		assert(list.size() == 2);
	}
	
	//tests basic functionality of getValue()
	@Test public void test3() throws ListException {
		LinkedList<String> list = new LinkedList<String>();
		list.addValue("first");
		list.addValue("second");
		list.addValue("third");
		
		assert(list.size() == 3);
		assert(list.getValue(1).equals("second"));
	}
	
	//tests getting the values at the beginning and end of the list
	@Test public void test4() throws ListException {
		LinkedList<String> list = new LinkedList<String>();
		list.addValue("first");
		list.addValue("second");
		list.addValue("third");
		list.addValue("fourth");
		
		assert(list.size() == 4);
		assert(list.getValue(0).equals("first"));
		assert(list.getValue(3).equals("fourth"));
	}
	
	//tests adding to a given index in the list
	@Test public void test5() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>(1);
		list.addValueAtIndex(3, 1);
		list.addValueAtIndex(2, 1);
		list.addValueAtIndex(5, 3);
		list.addValueAtIndex(4, 3);
		
		assert(list.getValue(0) == 1);
		assert(list.getValue(1) == 2);
		assert(list.getValue(2) == 3);
		assert(list.getValue(3) == 4);
		assert(list.getValue(4) == 5);
	}
	
	//tests adding values to the beginning of a list
	@Test public void test6() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addValueAtIndex(5, 0);
		list.addValueAtIndex(4, 0);
		list.addValueAtIndex(3, 0);
		list.addValueAtIndex(2, 0);
		list.addValueAtIndex(1, 0);
		
		assert(list.getValue(0) == 1);
		assert(list.getValue(1) == 2);
		assert(list.getValue(2) == 3);
		assert(list.getValue(3) == 4);
		assert(list.getValue(4) == 5);
	}
	
	//tests removing a value from a list
	@Test public void test7() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addValue(1);
		list.addValue(2);
		list.addValue(3);
		list.addValue(4);
		list.addValue(5);
		
		list.removeValue(2);
		list.removeValue(2);
		
		assert(list.size() == 3);
		assert(list.getValue(0) == 1);
		assert(list.getValue(1) == 2);
		assert(list.getValue(2) == 5);
	}
	
	//tests removing a value from the beginning of a list
	@Test public void test8() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addValue(1);
		list.addValue(2);
		list.addValue(3);
		list.addValue(4);
		list.addValue(5);
		
		list.removeValue(4);
		list.removeValue(0);
		
		assert(list.size() == 3);
		assert(list.getValue(0) == 2);
		assert(list.getValue(1) == 3);
		assert(list.getValue(2) == 4);
	}
	
	//tests valueExists for when it's true and when it is not
	@Test public void test9() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addValue(1);
		list.addValue(2);
		list.addValue(3);
		list.addValue(4);
		list.addValue(5);
		
		assert(list.size() == 5);
		assert(list.valueExists(3));
		assert(list.valueExists(1));
		assert(list.valueExists(5));
		assert(!list.valueExists(6));
		
		list.removeValue(4);
		assert(!list.valueExists(5));
	}
	
	//tests functionality of clear()
	@Test public void test10() throws ListException {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addValue(1);
		list.addValue(2);
		list.addValue(3);
		list.addValue(4);
		list.addValue(5);		
		assert(list.size() == 5);
		
		list.clear();
		assert(list.size() == 0);
		assert(!list.valueExists(2));
	}
}
