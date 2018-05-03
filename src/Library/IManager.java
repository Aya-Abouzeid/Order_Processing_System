package Library;

import java.util.Set;

import Library.Book.Category;

public interface IManager {

	/*
	 * @param book to add
	 * 
	 * @return true upon success,false otherwise
	 */
	public boolean addBook(Book book);

	/*
	 * @param book to update
	 * 
	 * @return true upon success,false otherwise
	 */
	public boolean updateBook(Book book);

	/*
	 * Promotes customer to Manager
	 * 
	 * @param userId id in user table
	 * 
	 * @return true upon success,false otherwise
	 *
	 */
	public boolean promoteUser(int userId);

	/*
	 * 
	 */
	public boolean confirmOrder(String isbn);

	/*
	 * 
	 */
	public boolean placeOrder(String isbn, int qunatity);

	/*
	 * @param isbn book ISBN in  table
	 * 
	 * @return Book 
	 * 
	 */
	public Book SearchByIsbn(String isbn);

	/*
	 * @param title book title
	 * @return Set<Book> set of books with the  title
	 */
	public Set<Book> searchByTitle(String title);

	/*
	 * @param cat Books' Category
	 * 
	 * @return Set<Book>  books with Category: cat
	 */
	public Set<Book> SearchByCategory(Category cat);

}
