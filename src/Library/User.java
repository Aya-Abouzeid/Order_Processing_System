package Library;

import java.sql.*;

public class User {

	protected int userID;
	protected Connection con;
	private Database db;
	public User(int id) throws SQLException, ClassNotFoundException  {
		userID = id;
		db = new Database();
		con = db.getCon();
	}
	
	//>> newly written
	ResultSet searchBook(String [] data) throws SQLException {
		
		String [] attributes = { "ISBN","PID","TITLE","YEAR","PRICE","CATEGORY","THRESHOLD","STOCK"};
		Statement stat = con.createStatement();
		String query = "SELECT * FROM BOOK WHERE ";
		for(int i = 0 ; i < data.length ; i++){
			
			if(!data[i].equals("")){
				if(i!=0)
					query+= " AND ";
				query+= attributes[i] + "='" + data[i] + "'";
			}
			
		}
		query+=";";
		return stat.executeQuery(query);
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>> we don't read price through GUI <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
// >> newly written
	int updateProfile(String [] data) throws SQLException {
		String [] attributes = { "UNAME","UPASS","EMAIL","FNAME","LNAME","ShippingAddress"};
		Statement stat = con.createStatement();
		String query = "update user set ";
		int counter =0;
		for(int i = 0 ; i < data.length ; i++){
			
			if(!data[i].equals("")){
				if(counter!=0)
					query+= ",";
				query+= attributes[i] + "='" + data[i] + "'";
				counter++;
			}
			
		}
		query+=" where UID = " + String.valueOf(userID) + ";";
		System.out.println(query);
		
		return stat.executeUpdate(query);
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> youssef wondering 
	int getItemPrice(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select Price From CART where ISBN = '" + isbn + "';";
		ResultSet result = stat.executeQuery(query);
		// review how to get specific element from result set
		
		return 0;
	}

	int getTotalPrice(int userID) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select sum(Price)*Quantity From CART where ISBN = '" + userID + "';";
		ResultSet result = stat.executeQuery(query);
		
		return 0;
	}

}