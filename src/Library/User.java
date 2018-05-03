package Library;

import java.sql.*;

public abstract class User {

	protected int userID;
	protected Connection con;

	public User(int id) throws SQLException {

		userID = id;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306" + "/OrderProcessingSystem", "root",
				"11feb2011");

	}

	int addInCart(int price, String ISBN, int id, int quantity) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query += "insert into Cart Values (" + String.valueOf(id)
		+ ISBN + String.valueOf(price) 
		+String.valueOf(quantity);
		
		return stat.executeUpdate(query);

	}

	int removeFromCart(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "delete from Cart where ISBN =" + isbn + ";";
		return stat.executeUpdate(query);

	}

	void logout(int userId) throws SQLException {

	}

	// what data to be sent ?
	int updateProfile() throws SQLException {
		return 0;
	}

	int getItemPrice(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select Price From Cart where ISBN = " + isbn + ";";
		ResultSet result = stat.executeQuery(query);
		// review how to get specific element from result set

		return 0;
	}

	int getTotalPrice(int userID) throws SQLException {

		//
		return 0;
	}

}