/*
 * John Curran
 * 
 * This defines the exception that is thrown for errors in trees
 */

package SortedTree;

@SuppressWarnings("serial")
public class TreeException extends Exception {
	protected TreeException(String message) {
		super(message);
	}
}
