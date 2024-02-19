package doaException;

public class NoSuchEmailException extends Exception {
	public NoSuchEmailException() {
		super("This mail does not exxists");
	}
}
