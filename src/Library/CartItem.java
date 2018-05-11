package Library;

public class CartItem {

	private String ISBN;
	private double price;
	private int quantity;
	private int uid;

	public CartItem(String isbn, int quant, int uid, double price) {

		this.ISBN = isbn;
		this.price = price;
		this.quantity = quant;
		this.uid = uid;
	}

	public void setIsbn(String isbn) {
		ISBN = isbn;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quan) {
		quantity = quan;
	}

	public void setUid(int id) {
		this.uid = id;
	}

	public int getUid() {
		return uid;
	}

	public double getPrice() {
		return price;
	}

	public String getIsbn() {
		return ISBN;
	}

	public int getQuantity() {
		return quantity;
	}
}
