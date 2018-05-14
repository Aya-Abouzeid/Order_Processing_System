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
	private double sellingPrice;
	private String category;
	private String year;

	// year ---> what type?
	// Book's Constructor
	public Book(String title, String isbn, int pubId, int stockQuantity
			, double price, int threshold, String cat,String date) {
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

	public void setCategory(String cat) {
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

	public void setYear(String date){
		this.year=date;
	}
	
	// getters

	public String getTitle() {
		return title;
	}
	
	public String getYear(){
		return this.year;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public int getThreshold() {
		return threshold;
	}

	public String getCategory() {
		return this.category;
	}

}