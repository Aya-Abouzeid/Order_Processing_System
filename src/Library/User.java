package Library;

import java.sql.*;
import java.util.*;

public class User {

	protected int userID;
	protected Connection con;
	private Database db;
	protected List<CartItem> cartItems;
	protected static String ERROR_MESSAGE;

	// I think this is a better way to know operation status,
	// make user functions return this enum instead of integers
	// not discussed yet
	public enum operation_status {
		SUCCESS, FAIL
	}

	public User(int id) throws SQLException, ClassNotFoundException {
		userID = id;
		db = new Database();
		con = db.getCon();
		cartItems = new ArrayList<>();
	}

	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}

	public void setErrorMessage(String message) {
		ERROR_MESSAGE = message;
	}

	// >> newly written
	ResultSet searchBook(String[] data) throws SQLException {
		try {
			String[] attributes = { "ISBN", "PID", "TITLE", "YEAR", "PRICE", "CATEGORY", "THRESHOLD", "STOCK" };
			Statement stat = con.createStatement();
			String query = "SELECT * FROM BOOK WHERE ";
			int j = -1;
			for (int i = 0; i < data.length; i++) {
				if (!data[i].equals("")) {
					j++;
					if (j != 0)
						query += " AND ";
					query += attributes[i] + "='" + data[i] + "'";
				}
			}
			query += ";";
			System.out.println(query);

			return stat.executeQuery(query);
		} catch (Exception e) {
			this.ERROR_MESSAGE = e.getMessage();
			return null;
		}

	}

	protected int addInCart(String ISBN, int id, int quantity) {

		// getting price first:
		double price = 0;
		for (int i = 0; i < cartItems.size(); i++) {
			if (cartItems.get(i).getIsbn().matches(ISBN)) {
				price = cartItems.get(i).getPrice();
				break;
				// add success and failure enum please xD
			}
		}
		CartItem item = new CartItem(ISBN, quantity, id, price);
		cartItems.add(item);
		return 0;
	}
	protected int removeFromCart(String isbn)  {

		for (int i = 0; i < cartItems.size(); i++) {
			if (cartItems.get(i).getIsbn().matches(isbn)) {
				cartItems.remove(i);
				// success
				return 0;
			}
		}
		// no item to remove
		return -1;
	}

	// I think more actions are needed on logging out
	protected void logout(int userId) {
		cartItems.clear();
	}

	// >> newly written
	protected int updateProfile(String[] data) throws SQLException {
		
		try {
		String[] attributes = { "UNAME", "UPASS", "EMAIL", "FNAME", "LNAME", "ShippingAddress" };
		Statement stat = con.createStatement();
		String query = "update user set ";
		int counter = 0;
		for (int i = 0; i < data.length; i++) {
			if (!data[i].equals("")) {
				if (counter != 0)
					query += ",";
				if (i == 1)
					query += attributes[i] + "=MD5('" + data[i] + "')";
				else
					query += attributes[i] + "='" + data[i] + "'";
				counter++;
			}
		}
		query += " where UID = " + String.valueOf(userID) + ";";
		System.out.println(query);
		return stat.executeUpdate(query);
		}catch (Exception e) {
			this.ERROR_MESSAGE=e.getMessage();
			return -1;
		}
	}

	// handling cases when items ate not found ? what to return
	protected double getItemPrice(String isbn) {
		for (int i = 0; i < cartItems.size(); i++) {
			if (cartItems.get(i).getIsbn().matches(isbn)) {
				return cartItems.get(i).getPrice();
			}
		}
		// item not found
		return -1;
	}

	protected double getTotalPrice(int userID) {
		double price = 0.0;
		int quantity;
		for (int i = 0; i < cartItems.size(); i++) {
			quantity = cartItems.get(i).getQuantity();
			price += (quantity) * cartItems.get(i).getPrice();
		}
		return price;
	}

	protected int confirmShopping() {

		return 0;
	}

}