package doaException;

import roles.Product;

public class StockNotAvailable extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotAvailable(Product product) {
		super(product.getAvailableQuantity()+" stock only availabe "+product.getName());
	}
}