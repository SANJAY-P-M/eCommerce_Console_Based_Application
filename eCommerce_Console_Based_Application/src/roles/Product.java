package roles;

public class Product {
	private String id;
	private String name;
	private String description;
	private double price;
	private double review;
	private int availableQuantity;
	
	public Product(String id,String name,String description,double price,double review,int availableQuantity){
		setId(id);
		setName(name);
		setDescription(description);
		setAvailableQuantity(availableQuantity);
		setPrice(price);
		setReview(review);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getReview() {
		return review;
	}
	public void setReview(double review) {
		this.review = review;
	}
	
	@Override
	public String toString() {
		 return "Product{" +
	                "id='" + id + '\'' +
	                ", name='" + name + '\'' +
	                ", description='" + description + '\'' +
	                ", price=" + price +
	                ", review=" + review +
	                ", availableQuantity=" + availableQuantity +
	                "}\n";
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int quantity) {
		this.availableQuantity = quantity;
	}
}
