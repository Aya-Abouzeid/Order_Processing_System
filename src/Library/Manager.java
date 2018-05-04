package Library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import Library.Book.Category;

public class Manager extends User implements IManager {

	public Manager(int id) throws SQLException, ClassNotFoundException {
		super(id);
	}

	@Override
	public int addBook(Book book) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query += "insert into BOOK  Values ( ' " + book.getIsbn() + " ', '" + book.getTitle() + "' ," + book.getpublisherId()
				+ " , '" + String.valueOf(book.getYear()) + "' , " + String.valueOf(book.getSellingPrice()) + " , '"
				+ String.valueOf(book.getCategory()) + "'," + String.valueOf(book.getStockQuantity()) + " , "
				+ String.valueOf(book.getThreshold()) + ");";

		return stat.executeUpdate(query);
	}

	public int addPublisher(int id,String lName,String fName,String address,String phone) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query+= "insert into BOOK_PUBLISHER values ("+String.valueOf(id)+",'"
				+ fName+"',"
				+ "'"+lName+" ',"
				+ "'"+address+"',"
				+ "'"+phone+"');";
		return stat.executeUpdate(query);
	}
	@Override
	public int updateBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		Statement stat = con.createStatement();
		String query = "";
		query += "Update  BOOK " + "set " + "ISBN = " + book.getIsbn() + "," + "Title = " + book.getTitle() + ","
				+ "Pid = " + book.getpublisherId() + "," + "Year = " + String.valueOf(book.getYear()) + "," + "Price = "
				+ String.valueOf(book.getSellingPrice()) + "," + "Category = " + String.valueOf(book.getCategory())
				+ "," + "Stock = " + String.valueOf(book.getStockQuantity()) + "," + "Threshold = "
				+ String.valueOf(book.getThreshold()) + ",);";

		return stat.executeUpdate(query);
	}

	@Override
	public int promoteUser(int userId) throws SQLException {

		// need other actions?
		Statement stat = con.createStatement();
		String query = "";
		query += "Insert into MANAGER Values " + String.valueOf(userId) + ";";
		return stat.executeUpdate(query);
		
	}

	@Override
	public int confirmOrder(String isbn) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Delete from BOOK_ORDERS where ISBN = " + isbn + ";";
		return stat.executeUpdate(query);
		
	}

	@Override
	public int placeOrder(String isbn, int qunatity) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Insert into BOOK_ORDERS Values " + String.valueOf(isbn) +","+ String.valueOf(qunatity) +  ";";
		return stat.executeUpdate(query);

	}

	@Override
	public ResultSet SearchByIsbn(String isbn) throws SQLException {

		Statement stat = con.createStatement();
		String query = "";
		query += "Select * from BOOK where ISBN = " + isbn + ";";
		return stat.executeQuery(query);
	}

	@Override
	public ResultSet searchByTitle(String title) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select * from BOOK where Title = " + title + ";";
		return stat.executeQuery(query);

	}

	@Override
	public ResultSet SearchByCategory(Category cat) throws SQLException {
		Statement stat = con.createStatement();
		String query = "";
		query += "Select * from BOOK where Category = ' " + cat + "';";
		return stat.executeQuery(query);

	}

}