package Library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.ResultSet;

public class DBMaster {

	private static Connection con;
	private Manager manager = null;
	private User LoggedIn;
	private int UID;
	private int MID;
	private static Database db;
	private static DBMaster dbm;

	private DBMaster() throws SQLException, ClassNotFoundException {

	}

	public static String ERROR_MESSAGE;

	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}

	public void setErrorMessage(String message) {
		ERROR_MESSAGE = message;
	}

	public int addInCart(String ISBN, int quantity) throws SQLException {
		return LoggedIn.addInCart(ISBN, quantity);
	}

	public static DBMaster getDBMaster() throws ClassNotFoundException, SQLException {
		if (dbm == null) {
			db = new Database();
			con = db.getCon();
			return dbm = new DBMaster();
		}
		return dbm;
	}

	// wrap it in userInfo bs b3d keda y3ny
	public int register(String uName, String uPass, String email, String fName, String lName, String address)
			throws ClassNotFoundException {

		try {
			Statement stat = con.createStatement();
			String query = "";

			query += "insert into USER (UName,UPass,Email,FName,LName,ShippingAddress)" + "values ( '" + uName + " ',"
					+ "MD5('" + uPass + "')," + "'" + email + " '," + "'" + fName + " '," + "'" + lName + " '," + "'"
					+ address + " ' );";

			int returnValue = stat.executeUpdate(query);
			String query2 = "";
			query2 += "select UID from USER where UName = '" + uName + "' and Upass = MD5('" + uPass + "');";

			ResultSet result = (ResultSet) stat.executeQuery(query2);

			if (!result.next()) { // empty set , NO SUCH USER
				return -1;
			}
			UID = result.getInt("UID");
			LoggedIn = new User(UID);
			return 1;
		} catch (SQLException e) {

			return -1;
		}

	}

	// search by email for now
	public int signIn(String UserName, String pass) throws ClassNotFoundException {
		try {
			Statement stat = con.createStatement();
			String query = "";
			query += "select UID from USER where UName = '" + UserName + "' and Upass = MD5('" + pass + "');";

			ResultSet result = (ResultSet) stat.executeQuery(query);
			if (!result.next()) { // empty set , NO SUCH USER
				return -1;
			}
			UID = result.getInt("UID");
			String query2 = "";
			query2 += "select MID from MANAGER where MID = " + String.valueOf(UID) + ";";
			ResultSet result2 = (ResultSet) stat.executeQuery(query2);

			if (result2.next()) { // Manager

				MID = result2.getInt("MID");

				LoggedIn = new Manager(MID);
				return 2;
			}

			LoggedIn = new User(UID);
			return 1;

		} catch (SQLException e) {

			return -2;
		}

	}

	public int confirmOrder(String isbn) throws SQLException {
		return ((Manager) LoggedIn).confirmOrder(isbn);
	}

	public int placeOrder(String isbn, int qunatity) throws SQLException {
		return ((Manager) LoggedIn).placeOrder(isbn, qunatity);
	}

	public int promoteUser(int userId) throws SQLException {
		return ((Manager) LoggedIn).promoteUser(userId);
	}

	public int removeFromCart(String isbn) throws SQLException {
		return LoggedIn.removeFromCart(isbn);
	}

	public void logOut() throws SQLException {
		LoggedIn.logout(UID);
		LoggedIn = null;
	}

	public void updateInfo(String[] data) throws SQLException {
		LoggedIn.updateProfile(data);
	}

	public int addBook(String[] data, String[] authors) throws SQLException {
		try {
			
			int success = ((Manager) LoggedIn).addBook(new Book(data[2], data[0], Integer.valueOf(data[1]),
					Integer.valueOf(data[7]), Double.valueOf(data[4]), Integer.valueOf(data[6]), data[5], data[3]),
					authors);
			return success;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public int modifyBook(String[] data, String[] authors) throws SQLException {
		System.out.println("here " + authors.length);
		for(int i =0 ; i<data.length;i++)
			System.out.println("test> "+data[i]);
		return ((Manager) LoggedIn).updateBook(data,authors);
	}

	public int confirmShopping() {
		return LoggedIn.confirmShopping();
	}

	public ResultSet searchBook(String[] data) throws SQLException {
		return (ResultSet) LoggedIn.searchBook(data);
	}

	public double getTotalPrice() throws SQLException {
		return LoggedIn.getTotalPrice(UID);
	}

	public double getItemPrice(String isbn) throws SQLException {
		return LoggedIn.getItemPrice(isbn);
	}

	public List<CartItem> getCart() {
		return LoggedIn.getCart();
	}

}
