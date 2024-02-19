package doaException;

public class MobileNumberAlreadyExistsException extends Exception {
	public MobileNumberAlreadyExistsException() {
		super("Mobile number already exists");
	}
}