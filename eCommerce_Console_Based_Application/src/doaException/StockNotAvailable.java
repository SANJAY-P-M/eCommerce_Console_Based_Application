package doaException;

public class StockNotAvailable extends Exception {
	public StockNotAvailable(String productId,int availableQuantity) {
		super(availableQuantity+" stock only availabe in productId "+productId);
	}
}