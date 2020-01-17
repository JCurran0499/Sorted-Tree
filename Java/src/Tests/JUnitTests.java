/*
 * John Curran
 * 
 * JUnit tests that test the functionality of the Sorted Tree class
 */

package Tests;

import org.junit.*;
import static org.junit.Assert.*;
import SortedTree.ListException;
import SortedTree.SortedTree;
import SortedTree.TreeException;
import java.util.ArrayList;

public class JUnitTests {
	
	//tests simply creating sorted trees using the 3 constructors
	@Test public void test1() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>();
		SortedTree<Integer> tree2 = new SortedTree<Integer>("Positives");
		SortedTree<Integer> tree3 = new SortedTree<Integer>(new String[] {"Positives", "Negatives"});
		
		assert(tree.size() == 0);
		assert(tree2.size() == 0);
		assert(tree3.size() == 0);
	}
	
	//tests adding some divisions and then values
	@Test public void test2() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>();
		tree.addDivision("Positives");
		tree.addDivision("Negatives");
		
		tree.addValue("Positives", 10);
		tree.addValue("Positives", 1);
		tree.addValue("Positives", 15);
		tree.addValue("Negatives", -10);
		tree.addValue("Negatives", -12);
		tree.addValue("Negatives", -6);
		
		assert(tree.size() == 6);
		assert(tree.size("Positives") == 3);
		assert(tree.size("Negatives") == 3);
	}
	
	//tests adding values after automatically adding divisions
	@Test public void test3() throws TreeException, ListException {
		//first with a single addition
		SortedTree<Integer> tree = new SortedTree<Integer>("Primes");
		
		tree.addValue("Primes", 7);
		tree.addValue("Primes", 2);
		tree.addValue("Primes", 3);
		tree.addValue("Primes", 23);
		tree.addValue("Primes", 19);
		tree.addValue("Primes", 17);
		
		assert(tree.size() == 6);
		assert(tree.size("Primes") == 6);
		
		
		//now with multiple additions at once
		SortedTree<Integer> tree2 = new SortedTree<Integer>(new String[] {"Positives", "Negatives"});
		
		tree2.addValue("Positives", 10);
		tree2.addValue("Positives", 1);
		tree2.addValue("Positives", 15);
		tree2.addValue("Negatives", -10);
		tree2.addValue("Negatives", -12);
		tree2.addValue("Negatives", -6);
		
		assert(tree2.size() == 6);
		assert(tree2.size("Positives") == 3);
		assert(tree2.size("Negatives") == 3);
	}
	
	//tests basic functionality of numDivisions
	@Test public void test4() throws TreeException, ListException {
		SortedTree<String> tree = new SortedTree<String>("Animals");
		tree.addValue("Animals", "Dog");
		tree.addValue("Animals", "Cat");
		tree.addValue("Animals", "Hamster");
		
		tree.addDivision("Inanimate Objects");
		tree.addValue("Inanimate Objects", "Couch");
		
		tree.addDivision("Fictional Things");
		tree.addValue("Fictional Things", "Dragon");
		tree.addValue("Fictional Things", "Magic");
		
		assert(tree.size() == 6);
		assert(tree.numDivisions() == 3);
	}
	
	//tests numDivisions and size when there are no divisions and no values
	@Test public void test5() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>();
		
		assert(tree.numDivisions() == 0);
		
		tree.addDivision("Primes");
		tree.addDivision("Positives");
		tree.addDivision("Negatives");
		assert(tree.size() == 0);
		assert(tree.size("Primes") == 0);
		assert(tree.size("Positives") == 0);
		assert(tree.size("Negatives") == 0);
	}
	
	//tests numDivisions after various constructor calls
	@Test public void test6() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Positives");
		SortedTree<Integer> tree2 = new SortedTree<Integer>(new String[] {"Positives", "Negatives"});
		
		assert(tree.numDivisions() == 1);
		assert(tree2.numDivisions() == 2);
	}
	
	//tests adding numbers out of order but using indexes to make it work
	@Test public void test7() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Main");
		tree.addValue("Main", 1);
		tree.addValueAtIndex("Main", 3, 1);
		tree.addValueAtIndex("Main", 2, 1);
		tree.addValueAtIndex("Main", 5, 3);
		tree.addValueAtIndex("Main", 4, 3);
		
		assert(tree.size("Main") == 5);
		assert(tree.getValue("Main", 0) == 1);
		assert(tree.getValue("Main", 1) == 2);
		assert(tree.getValue("Main", 2) == 3);
		assert(tree.getValue("Main", 3) == 4);
		assert(tree.getValue("Main", 4) == 5);
	}
	
	//tests adding values to the beginning of the list in a division
	@Test public void test8() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Main");
		tree.addValueAtIndex("Main", 5, 0);
		tree.addValueAtIndex("Main", 4, 0);
		tree.addValueAtIndex("Main", 3, 0);
		tree.addValueAtIndex("Main", 2, 0);
		tree.addValueAtIndex("Main", 1, 0);
		
		assert(tree.size("Main") == 5);
		assert(tree.getValue("Main", 0) == 1);
		assert(tree.getValue("Main", 1) == 2);
		assert(tree.getValue("Main", 2) == 3);
		assert(tree.getValue("Main", 3) == 4);
		assert(tree.getValue("Main", 4) == 5);
	}
	
	//tests basic divisionExists calls
	@Test public void test9() throws TreeException {
		SortedTree<Character> tree = new SortedTree<Character>("Vowels");
		assert(tree.divisionExists("Vowels"));
		assert(!tree.divisionExists("Consonants"));
	}
	
	//tests basic valueExists calls
	@Test public void test10() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		
		//exists
		assert(tree.valueExists("Vowels", 'i'));
		//doesn't exist
		assert(!tree.valueExists("Vowels", 'x'));
		//exists, but not in the right division
		assert(!tree.valueExists("Consonants", 'a'));
	}
	
	//tests removing a division
	@Test public void test11() throws TreeException {
		SortedTree<String> tree = new SortedTree<String>();
		tree.addDivision("States");
		tree.addDivision("Countries");
		tree.addDivision("Cities");
		tree.addDivision("Towns");
		tree.addDivision("Planets");
		assert(tree.numDivisions() == 5);
		
		tree.removeDivision("Countries");
		assert(tree.numDivisions() == 4);
		assert(!tree.divisionExists("Countries"));
		
		tree.removeDivision("Planets");
		assert(tree.numDivisions() == 3);
		assert(!tree.divisionExists("Planets"));
	}
	
	//tests removing a division at the root of the tree
	@Test public void test12() throws TreeException {
		SortedTree<String> tree = new SortedTree<String>();
		tree.addDivision("States");
		tree.addDivision("Countries");
		tree.addDivision("Cities");
		tree.addDivision("Towns");
		tree.addDivision("Planets");
		assert(tree.numDivisions() == 5);
		
		tree.removeDivision("States");
		assert(tree.numDivisions() == 4);
		assert(!tree.divisionExists("States"));
	}
	
	//tests removing a division that was put in place by the constructor
	@Test public void test13() throws TreeException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Primes");
		tree.removeDivision("Primes");
		assert(tree.numDivisions() == 0);
	}
	
	//tests that removing a division also clears all of its values
	@Test public void test14() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		assert(tree.size() == 6);
		
		tree.removeDivision("Vowels");
		assert(!tree.divisionExists("Vowels"));
		assert(tree.numDivisions() == 1);
		assert(tree.size() == 2);
	}
	
	//tests removing a value from a given division
	@Test public void test15() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		assert(tree.size("Vowels") == 4);
		assert(tree.size() == 6);
		
		tree.removeValue("Vowels", 1);
		assert(tree.size("Vowels") == 3);
		assert(tree.size() == 5);
		assert(!tree.valueExists("Vowels", 'e'));
	}
	
	//tests removing values from the beginning and end of a division
	@Test public void test16() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		assert(tree.size("Vowels") == 4);
		assert(tree.size() == 6);
		
		tree.removeValue("Vowels", 3);
		assert(tree.size("Vowels") == 3);
		assert(tree.size() == 5);
		assert(!tree.valueExists("Vowels", 'o'));
		
		tree.removeValue("Vowels", 0);
		assert(tree.size("Vowels") == 2);
		assert(tree.size() == 4);
		assert(!tree.valueExists("Vowels", 'a'));
	}
	
	//tests basic functionality of clear()
	@Test public void test17() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		assert(tree.numDivisions() == 2);
		assert(tree.size() == 6);
		
		tree.clear();
		assert(tree.numDivisions() == 0);
		assert(tree.size() == 0);
		tree.addDivision("Vowels"); //this should not be an error
	}
	
	//tests turning a division with values into an ArrayList
	@Test public void test18() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		tree.addValue("Consonants", 'b');
		tree.addValue("Consonants", 'c');
		
		ArrayList<Character> array = tree.toArrayList("Vowels");
		assert(array.size() == 4);
		assert(array.get(0) == 'a');
		assert(array.get(1) == 'e');
		assert(array.get(2) == 'i');
		assert(array.get(3) == 'o');
	}
	
	//tests toArrayList using an empty division
	@Test public void test19() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		tree.addDivision("Consonants");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		
		ArrayList<Character> array = tree.toArrayList("Consonants");
		assert(array.size() == 0);
	}
	
	//the rest of the tests from this point on are error cases
	
	@SuppressWarnings("unused")
	//tests invalid constructor arguments
	@Test public void test20() throws TreeException {
		try {
			SortedTree<Integer> tree = new SortedTree<Integer>(new String[] {"Repeat", "Repeat"});
			fail(); //shouldn't be able to reach this point
		} catch (Exception e) {}
		
		try {
			SortedTree<Integer> tree = new SortedTree<Integer>("");
			fail(); //shouldn't be able to reach this point
		} catch (Exception e) {}
		
		try {
			SortedTree<Integer> tree = new SortedTree<Integer>(new String[] {"Repeat", "Repeat2", ""});
			fail(); //shouldn't be able to reach this point
		} catch (Exception e) {}
		
		try {
			SortedTree<Integer> tree = new SortedTree<Integer>(new String[] {null, "Repeat2", ""});
			fail(); //shouldn't be able to reach this point
		} catch (Exception e) {}
	}
	
	//tests adding divisions of the same name
	@Test public void test21() throws TreeException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Repeat");
		try {
			tree.addDivision("Repeat");
			fail();
		} catch (Exception e) {}
		
		assert(tree.numDivisions() == 1);
		
		tree.addDivision("Repeat2");
		try {
			tree.addDivision("Repeat2");
			fail();
		} catch (Exception e) {}
		
		assert(tree.numDivisions() == 2);
	}
	
	//tests adding invalid division names
	@Test public void test22() throws TreeException {
		SortedTree<Integer> tree = new SortedTree<Integer>();
		try {
			tree.addDivision("");
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addDivision(null);
			fail();
		} catch (Exception e) {}
		
		assert(tree.numDivisions() == 0);
	}
	
	//tests calling the size of a division that does not exist or is null
	@Test public void test23() throws TreeException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Positives");
		try {
			tree.size("Negatives");
			fail();
		} catch (Exception e) {}
		
		try {
			tree.size(null);
			fail();
		} catch (Exception e) {}
	}
	
	//tests adding values to a division that doesn't exist
	@Test public void test24() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Positives");
		try {
			tree.addValue("Negatives", 7);
			fail();
		} catch (Exception e) {}
	}
	
	//tests invalid parameters for addValue()
	@Test public void test25() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Positives");
		try {
			tree.addValue(null, 7);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValue(null, null);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValue("Positives", null);
			fail();
		} catch (Exception e) {}
	}
	
	//does the same as tests 24 and 25, except with addValueAtIndex()
	@Test public void test26() throws TreeException, ListException {
		SortedTree<Integer> tree = new SortedTree<Integer>("Positives");
		try {
			tree.addValueAtIndex("Negatives", 7, 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValueAtIndex(null, 7, 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValueAtIndex(null, null, 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValueAtIndex("Positives", null, 0);
			fail();
		} catch (Exception e) {}
	}
	
	//tests invalid indexes on otherwise valid sorted trees
	@Test public void test27() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		
		try {
			tree.addValueAtIndex("Vowels", 'u', 5);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.addValueAtIndex("Vowels", 'u', -1);
			fail();
		} catch (Exception e) {}
	}
	
	//tests getting a value from a nonexistent division, a null division,
	//and from an index that is invalid
	@Test public void test28() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		
		try {
			tree.getValue("Consonants", 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.getValue(null, 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.getValue("Vowels", 5);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.getValue("Vowels", -1);
			fail();
		} catch (Exception e) {}
		
		tree.clear();
		tree.addDivision("Vowels"); //empty division
		
		try {
			tree.getValue("Vowels", 0);
			fail();
		} catch (Exception e) {}
	}
	
	//tests checking to see if a null division exists
	@Test public void test29() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		try {
			assert(tree.divisionExists(null));
			fail();
		} catch (Exception e) {}
	}
	
	//tests checking if a value exists from a nonexistent division, 
	//a null division, and a null value
	@Test public void test30() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		
		try {
			assert(tree.valueExists("Consonants", 'a'));
			fail();
		} catch (Exception e) {}
		
		try {
			assert(tree.valueExists(null, 'a'));
			fail();
		} catch (Exception e) {}
		
		try {
			assert(tree.valueExists("Vowels", null));
			fail();
		} catch (Exception e) {}
	}
	
	//tests removing an invalid and null division
	@Test public void test31() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>("Vowels");
		try {
			tree.removeDivision("Consonants");
			fail();
		} catch (Exception e) {}
		
		try {
			tree.removeDivision(null);
			fail();
		} catch (Exception e) {}
	}
	
	//tests removing a value from a nonexistent and null division
	@Test public void test32() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		
		try {
			tree.removeValue("Consonants", 0);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.removeValue(null, 0);
			fail();
		} catch (Exception e) {}
	}
	
	//tests removing from an improper index in a division
	@Test public void test33() throws TreeException, ListException {
		SortedTree<Character> tree = new SortedTree<Character>();
		tree.addDivision("Vowels");
		
		tree.addValue("Vowels", 'a');
		tree.addValue("Vowels", 'e');
		tree.addValue("Vowels", 'i');
		tree.addValue("Vowels", 'o');
		
		try {
			tree.removeValue("Vowels", 4);
			fail();
		} catch (Exception e) {}
		
		try {
			tree.removeValue("Vowels", -1);
			fail();
		} catch (Exception e) {}
	}
}
