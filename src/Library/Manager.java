package Library;

import java.sql.ResultSet;
import java.time.Period;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.util.Set;

import Library.Book.Category;

public class Manager extends User implements IManager {

	public Manager(int id) throws SQLException, ClassNotFoundException {

		super(id);
		delete();
	}
	private void delete() {
        LocalDate currentDate = LocalDate.now();
		Statement stat;
		try {
			stat = con.createStatement();
			Statement stat2 = con.createStatement();

			String query = "select * from BOOKS_SOLD ;";
			ResultSet rs = stat.executeQuery(query);
			query = "";
			while(rs.next()){
				Period period = Period.between ( currentDate , rs.getDate(3).toLocalDate() );
				int monthsElapsed = period.getMonths() ;
				System.out.println(period.getMonths() +" "+ period.getDays()+" "+period.getYears() );
				if(Math.abs(monthsElapsed) > 3 || (Math.abs(monthsElapsed) == 3 && 
						period.getDays()!=0) || period.getYears() !=0)
				{
					query = "delete from BOOKS_SOLD where UID = "+rs.getInt(1)+" and ISBN = '"+rs.getString(2)+"' ;";
					stat2.executeUpdate(query);
					System.out.println(query);
					query = "";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public int addBook(Book book, String[] authors) throws SQLException {

		try {

			// con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement("insert into BOOK values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getTitle());
			stmt.setInt(3, book.getPublisherId());
			stmt.setString(4, book.getYear());
			stmt.setDouble(5, book.getSellingPrice());
			stmt.setString(6, book.getCategory());
			stmt.setInt(7, book.getStockQuantity());
			stmt.setInt(8, book.getThreshold());

			stmt.executeUpdate();

			for (int i = 0; i < authors.length; i++) {
				PreparedStatement stmt2 = con.prepareStatement("insert into BOOK_AUTHORS values(?,?)");
				stmt2.setString(1, book.getIsbn());
				stmt2.setString(2, authors[i]);
				stmt2.executeUpdate();
			}
			return 1;

		} catch (SQLException e) {

			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		} catch (NumberFormatException e) {
			DBMaster.ERROR_MESSAGE = "Invalid Input";
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
		} catch (NumberFormatException e) {
			DBMaster.ERROR_MESSAGE = "Invalid Input";
			return -1;
		}
	}

	@Override
	public int updateBook(String[] data, String[] authors) {
		try {

			String[] attributes = { "ISBN", "PID", "Title", "Year", "Price", "Category", "Threshold", "Stock" };

			Statement stat = con.createStatement();
			String query = "update book set ";
			int counter = 0;

			for (int i = 1; i < data.length; i++) {
				if (!data[i].equals("")) {
					if (counter == 0) {
						if (i == 2 || i == 3 || i == 5)
							query += attributes[i] + "='" + data[i] + "'";
						else
							query += attributes[i] + "=" + data[i];
					} else {
						if (i == 2 || i == 3 || i == 5)
							query += "," + attributes[i] + "='" + data[i] + "'";
						else
							query += "," + attributes[i] + "=" + data[i];
					}
					counter++;
				}
			}

			query = query.substring(0, query.length());
			query += " where ISBN = '" + data[0] + "';";
			if (counter != 0)
				stat.executeUpdate(query);
			if (authors.length > 0) {
				String query2 = "delete from BOOK_AUTHORS where ISBN = '" + data[0] + "';";
				stat.executeUpdate(query2);

				for (int i = 0; i < authors.length; i++) {
					PreparedStatement stmt2 = con.prepareStatement("insert into BOOK_AUTHORS values(?,?)");
					stmt2.setString(1, data[0]);
					stmt2.setString(2, authors[i]);
					stmt2.executeUpdate();
				}
			}
			return 1;
		} catch (Exception e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return -1;
		}
	}

	@Override
	public int promoteUser(int userId) throws SQLException {

		// need other actions?
		try {

			PreparedStatement stmt = con.prepareStatement("insert into MANAGER values(?)");
			stmt.setInt(1, userId);
			stmt.executeUpdate();
			return 1;
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

			query += "Delete from BOOK_ORDERS where ISBN = '" + isbn + "';";
			int number = stat.executeUpdate(query);
			return number;
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
			System.out.println(" select * from book_orders where isbn = '"+ isbn+"';");

			ResultSet exists = stat.executeQuery(" select * from book_orders where isbn = '"+ isbn+"' ;");
			//System.out.println(exists);

			if(exists.next()){
				query = "update BOOK_ORDERS set quantity = quantity + "+String.valueOf(qunatity)+" where isbn = "+isbn;
			}
            
		else
			query += "Insert into BOOK_ORDERS Values ('" + isbn + "'," + String.valueOf(qunatity) + ");";
			
			System.out.println(query);
			int number = stat.executeUpdate(query);
			return 1;
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
					+ " from BOOKS_SOLD" + "group by ISBN" + ")t1" + "on t1.ISBN = BOOK.ISBN"
					+ " ORDER BY S DESC LIMIT 5;";

			return stat.executeQuery(query);
		} catch (SQLException e) {
			DBMaster.ERROR_MESSAGE = e.getMessage();
			return null;
		}
	}

}