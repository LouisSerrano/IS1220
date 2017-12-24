package clui;

public class InvalidCommandException extends Exception {
	public InvalidCommandException() {
		super("Given arguments don't match specified syntax");
	}
}
