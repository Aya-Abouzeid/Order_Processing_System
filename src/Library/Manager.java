package Library;

import java.sql.SQLException;
import java.util.Set;

import Library.Book.Category;

public class Manager extends User implements IManager {

	public Manager(int id) throws SQLException {
		super(id);
		
	}
	
	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promoteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean confirmOrder(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean placeOrder(String isbn, int qunatity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book SearchByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> SearchByCategory(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

}