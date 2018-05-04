package Library;

import java.sql.*;

public abstract class User {

	protected int userID;
	protected Connection con;
	private Database db;

	public User(int id) throws SQLException, ClassNotFoundException  {

		userID = id;
		db = new Database();
		con = db.getCon();

	}

	int addInCart(int price, String ISBN, int id, int quantity) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query += "insert into CART Values (" + String.valueOf(id)+ " , " + ISBN + " , "+ String.valueOf(price) + " , "
				+ String.valueOf(quantity) +");";
		

		return stat.executeUpdate(query);

	}

	int removeFromCart(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "delete from CART where ISBN ='" + isbn + "';";
		return stat.executeUpdate(query);

	}

	void logout(int userId) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query += "delete from CART where UID =" + userId + ";";
		stat.executeUpdate(query);

	}

	// what data to be sent ?
	int updateProfile(String [] data) throws SQLException {
		
		return 0;
	}

	int getItemPrice(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select Price From CART where ISBN = '" + isbn + "';";
		ResultSet result = stat.executeQuery(query);
		// review how to get specific element from result set
		
		return 0;
	}

	int getTotalPrice(int userID) throws SQLException {

		//
		return 0;
	}

}