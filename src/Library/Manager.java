package Library;

import java.util.Set;

import Library.Book.Category;

public class Manager implements IManager {


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
