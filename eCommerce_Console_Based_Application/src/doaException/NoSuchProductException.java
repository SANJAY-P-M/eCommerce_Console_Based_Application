package doaException;

public class NoSuchProductException extends Exception {
	public NoSuchProductException(String productId) {
		super("product not found with productId : "+productId);
	}
}