package Library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Set;

import Library.Book.Category;

public class Manager extends User implements IManager {

	public Manager(int id) throws SQLException, ClassNotFoundException {

		super(id);
	}

	@Override
	public int addBook(Book book) throws SQLException {

		try {

			PreparedStatement stmt = con.prepareStatement("insert into BOOK values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setInt(3, book.getPublisherId());
			stmt.setString(4, book.getYear());
			stmt.setDouble(5, book.getSellingPrice());
			stmt.setString(6, book.getCategory());
			stmt.setInt(7, book.getStockQuantity());
			stmt.setInt(8, book.getThreshold());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("heere 33");

			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}
	}

	public int addPublisher(int id, String lName, String fName, String address, String phone) {

		try {
			Statement stat = con.createStatement();
			PreparedStatement stmt = con.prepareStatement("insert into BOOK_PUBLISHER values(?,?,?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, fName);
			stmt.setString(3, lName);
			stmt.setString(4, address);
			stmt.setString(5, phone);
			return stmt.executeUpdate();

		} catch (SQLException e) {

			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}
	}

	@Override
	public int updateBook(String[] data) {
		try {
			String[] attributes = { "ISBN", "PID", "Title", "Year", "Price", "Category", "Threshold", "Stock" };
			Statement stat = con.createStatement();
			String query = "update book set ";
			int counter = 0;
			for (int i = 1; i < data.length; i++) {
				if (!data[i].equals("")) {
					if (counter != 0)
						query += ",";

					else
						query += attributes[i] + "='" + data[i] + "'";
					counter++;
				}
			}
			query += " where ISBN = " + data[0] + ";";
			System.out.println(query);
			return stat.executeUpdate(query);
		} catch (Exception e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}
	}

	@Override
	public int promoteUser(int userId) throws SQLException {

		// need other actions?
		try {
			Statement stat = con.createStatement();
			String query = "";
			// handle if user id is not valid
			query += "Insert into MANAGER Values " + String.valueOf(userId) + ";";
			return stat.executeUpdate(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}

	}

	@Override
	public int confirmOrder(String isbn) throws SQLException {

		try {
			Statement stat = con.createStatement();
			String query = "";
			// handle if isbn is not valid

			query += "Delete from BOOK_ORDERS where ISBN = '" + isbn + "';";
			return stat.executeUpdate(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;

		}

	}

	@Override
	public int placeOrder(String isbn, int qunatity) {

		try {

			Statement stat = con.createStatement();
			String query = "";
			query += "Insert into BOOK_ORDERS Values ('" + isbn + "'," + String.valueOf(qunatity) + ";";
			return stat.executeUpdate(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}

	}

	@Override
	public ResultSet SearchByIsbn(String isbn) {

		try {
			Statement stat = con.createStatement();
			String query = "";
			query += "Select * from BOOK where ISBN = '" + isbn + "';";
			return stat.executeQuery(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}
	}

	@Override
	public ResultSet searchByTitle(String title) {

		try {
			Statement stat = con.createStatement();
			String query = "";
			query += "Select * from BOOK where Title = '" + title + "';";

			return stat.executeQuery(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}

	}

	@Override
	public ResultSet SearchByCategory(Category cat) {
		try {
			Statement stat = con.createStatement();
			String query = "";
			query += "Select * from BOOK where Category = ' " + cat + "';";
			return stat.executeQuery(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}

	}

	public double getTotalSales() {

		try {
			Statement stat = con.createStatement();
			String query = "select SUM(Price) as total_sales from BOOKS_SOLD;";
			ResultSet r = stat.executeQuery(query);

			// not tested yet yet
			return r.getDouble("total_sales");

		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}
	}

	@Override
	public ResultSet getTopCustomers() {
		try {
			Statement stat = con.createStatement();
			String query = "select USER.LName,t1.UID,t1.S" + "from USER " + "join" + "(select UID,SUM(Quantity) AS S"
					+ "from BOOKS_SOLD" + "group by UID" + ")t1" + "on t1.UID = USER.UID" + "ORDER BY S DESC LIMIT 5;";

			return stat.executeQuery(query);

		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}
	}

	// doesn't work yet
	@Override
	public ResultSet getBestSellingBooks() {

		try {
			Statement stat = con.createStatement();
			String query = "select BOOK.Title,t1.S from BOOK" + "join" + "(select ISBN,SUM(Quantity) AS S"
					+ "from BOOKS_SOLD" + "group by ISBN" + ")t1" + "on t1.ISBN = BOOK.ISBN"
					+ "ORDER BY S DESC LIMIT 5;";

			return stat.executeQuery(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}
	}

}