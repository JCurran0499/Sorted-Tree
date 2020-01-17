/*
 * John Curran
 * 
 * This defines the exception that is thrown for errors in linked lists
 */

package SortedTree;

@SuppressWarnings("serial")
public class ListException extends Exception {
	protected ListException(String message) {
		super(message);
	}
}
