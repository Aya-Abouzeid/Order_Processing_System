package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public Connection con;
	public Connection getCon(){
	try {
	Class.forName("com.mysql.jdbc.Driver");
	con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/OrderProcessingSystem", "root", "12345");
	} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return con;
	}
}