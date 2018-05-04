package Library;

import java.sql.Date;

public class Book {

	// defining book attributes

	public enum Category {
		Science, Art, Religion, History, Geography
	}

	private String isbn;
	private String title;
	private int publisherId;
	private int threshold;
	private int stockQuantity;
	private int sellingPrice;
	private Category category;
	private Date year;

	// year ---> what type?
	// Book's Constructor
	public Book(String title, String isbn, int pubId, int stockQuantity
			, int price, int threshold, Category cat,Date date) {
		this.category = cat;
		this.title = title;
		this.isbn = isbn;
		this.threshold = threshold;
		this.sellingPrice = price;
		this.publisherId = pubId;
		this.stockQuantity = stockQuantity;
		this.year=date;
		//this.year= 
	}
	public Book(){
		
	}

	// setters for Book
	public void setTitle(String title) {

		this.title = title;
	}

	public void setCategory(Category cat) {
		this.category = cat;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setStockQuantity(int quant) {

		this.stockQuantity = quant;
	}

	public void setSellingPrice(int price) {

		this.sellingPrice = price;
	}

	public void setPublisherId(int id) {
		this.publisherId = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setYear(Date date){
		this.year=date;
	}
	
	// getters

	public String getTitle() {
		return title;
	}
	
	public Date getYear(){
		return this.year;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getpublisherId() {
		return publisherId;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public int getThreshold() {
		return threshold;
	}

	public Category getCategory() {
		return this.category;
	}

}