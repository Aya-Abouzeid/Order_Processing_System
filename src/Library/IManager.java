package Library;

import java.util.Set;
import java.sql.*;
import Library.Book.Category;

public interface IManager {

	/*
	 * @param book to add
	 * 
	 * @return true upon success,false otherwise
	 */
	public int addBook(Book book) throws SQLException;

	/*
	 * @param book to update
	 * 
	 * @return true upon success,false otherwise
	 */
	public int updateBook(String [] data) throws SQLException;

	/*
	 * Promotes customer to Manager
	 * 
	 * @param userId id in user table
	 * 
	 * @return true upon success,false otherwise
	 *
	 */
	public int promoteUser(int userId) throws SQLException;

	/*
	 * 
	 */
	public int confirmOrder(String isbn) throws SQLException;

	/*
	 * 
	 */
	public int placeOrder(String isbn, int qunatity) throws SQLException;

	/*
	 * @param isbn book ISBN in table
	 * 
	 * @return Book
	 * 
	 */
	public ResultSet SearchByIsbn(String isbn) throws SQLException;

	/*
	 * @param title book title
	 * 
	 * @return Set<Book> set of books with the title
	 */
	public ResultSet searchByTitle(String title) throws SQLException;

	/*
	 * @param cat Books' Category
	 * 
	 * @return Set<Book> books with Category: cat
	 */
	public ResultSet SearchByCategory(Category cat) throws SQLException;

	public double getTotalSales();

	public ResultSet getTopCustomers();

	public ResultSet getBestSellingBooks();

}