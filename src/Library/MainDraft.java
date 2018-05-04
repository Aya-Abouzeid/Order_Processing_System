package Library;

import java.sql.Date;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import Library.Manager;
import Library.User;
import Library.Book;

public class MainDraft {

	public static void main(String[] arg) throws SQLException, ClassNotFoundException {
		System.out.println("hellooooooooooo");
		MainDraft draft = new MainDraft();
		draft.init();
	

	}

	public int init() throws SQLException, ClassNotFoundException {
		Manager m = new Manager(2);
		
		Book b = new Book();
		b.setIsbn("91412125-122");
		b.setPublisherId(123);
		b.setTitle("title2");
		b.setSellingPrice(150);
		b.setThreshold(10);
		b.setStockQuantity(10);
		b.setCategory(Book.Category.Art);
		b.setYear(new Date(2000));
		
		//m.addPublisher(18,"qqweqwesd", "asd", "asd", "12331");
		System.out.println("passed");
		
		try {
			m.addBook(b);
		}catch (SQLException e){
			String ex = e.getMessage();
			System.out.println(ex);
			
		}
		
		//m.logout(m.userID);
		//m.removeFromCart("1234");
		//m.addInCart(100, "12345", m.userID, 2);
		//System.out.println(m.signIn("aykalb@gmail.com"));
		
		return 0;
		
	
	}

}
