package Library;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User {

	protected int userID;
	protected Connection con;
	private Database db;
	protected List<CartItem> cartItems;

	public User(int id) throws SQLException, ClassNotFoundException {

		userID = id;
		db = new Database();
		con = db.getCon();
		cartItems = new ArrayList<>();
	}

	// >> newly written
	ResultSet searchBook(String[] data) throws SQLException {
		try {
			String[] attributes = { "ISBN", "PID", "TITLE", "YEAR", "PRICE", "CATEGORY", "THRESHOLD", "STOCK" , "AUTHOR"};
			Statement stat = con.createStatement();
			String query = "";
			if(data[8].equals("")){
			query = "SELECT * FROM BOOK WHERE ";
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

			}
			else {
				query = "SELECT * FROM BOOK NATURAL JOIN BOOK_AUTHORS WHERE ";
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
			}
			return stat.executeQuery(query);

		} catch (Exception e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}

	}

	protected List<CartItem> getCart() {
		return cartItems;
	}

	protected int addInCart(String ISBN, int quantity) throws SQLException {
		Statement stat = con.createStatement();
		String query = "select ISBN , stock , price from BOOK where isbn = '" + ISBN + "'";
		System.out.println(query);
		ResultSet x = stat.executeQuery(query);
		if (x.next()) {
			double price = x.getDouble("price");
			int q = 0;
			int index = -1;
			for (int i = 0; i < cartItems.size(); i++) {
				if (cartItems.get(i).getIsbn().matches(ISBN)) {
					q += cartItems.get(i).getQuantity();
					index = i;
					break;
					// add success and failure enum please xD
				}
			}
			if (x.getInt("stock") - q - quantity >= 0) {
				if (index!=-1){
					cartItems.get(index).setQuantity(q+quantity);
					
				}
				else{
				CartItem item = new CartItem(ISBN, quantity, userID, price);
				cartItems.add(item);
				}
			} else
				return -1;
		} else
			return -2;

		return 0;
	}

	protected int removeFromCart(String isbn) {

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
			return stat.executeUpdate(query);
		} catch (Exception e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
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

		try {
			con.setAutoCommit(false);
			int quantity;
			String ISBN;
			String query="" , query2="";
	        LocalDate currentDate = LocalDate.now();

			Statement stat = con.createStatement();
			if( cartItems.size() == 0)
				return -1;
			System.out.println(cartItems.size());
			
			for (int i = 0; i < cartItems.size(); i++) {
				ISBN = cartItems.get(i).getIsbn();
				quantity = cartItems.get(i).getQuantity();
				query = "update BOOK set Stock = Stock - " + String.valueOf(quantity) + " where ISBN ='" + cartItems.get(i).getIsbn() +"';";
				System.out.println(query);
				if(((ResultSet)stat.executeQuery("select UID , ISBN from BOOKS_sOLD where UID = "+ userID +" and ISBN = " +cartItems.get(i).getIsbn())).next()){
					
					query2 = "update books_sold set quantity = quantity + " + cartItems.get(i).getQuantity() + " where uid = "+ userID +" and ISBN = " +cartItems.get(i).getIsbn();
					//System.out.println(Integer.valueOf(currentDate.toString()));

				}
				else
				query2 = "insert into BOOKS_SOLD values (" + userID + ",'" + cartItems.get(i).getIsbn()
						  + "','" + currentDate + "',"+ cartItems.get(i).getPrice() + "," + cartItems.get(i).getQuantity()+");";

			
//				LocalDate weekLater = currentDate.plusDays ( 7 );
//				Period period = Period.between ( currentDate , weekLater );
//				double daysElapsed = period.getDays () / 30.0;
//
//				System.out.println ( "localDate: " + currentDate + " to " + weekLater + " in days: " + daysElapsed );
				stat.executeUpdate(query);
				stat.executeUpdate(query2);


			}
			con.commit();
			cartItems.clear();
			return 1;
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			//System.out.println(e.getMessage());
			return -1;

		}
	}

}
