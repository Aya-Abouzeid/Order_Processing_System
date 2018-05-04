package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class DBMaster {

	private Connection con;
	private Manager manager = null;
	private User user;
	public DBMaster() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver"); 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306" + "/OrderProcessingSystem", "root",
				"11feb2011");
		
	}
	
	//wrap it in userInfo bs b3d keda y3ny
	private int register(String uName,String uPass,
			String email,String fName,String lName,String address ){
		
		try {
			Statement stat = con.createStatement();
			String query = "";
			query+="insert into USER (UName,UPass,Email,FName,LName,ShippingAddress)"
					+ "values ( '"+uName +" ',"
					+"'"+uPass +" ',"
					+"'"+email +" ',"
					+"'"+fName +" ',"
					+"'"+lName +" ',"
					+"'"+address +" ' );";
			return stat.executeUpdate(query);
		}catch (SQLException e){
			
			return -1;
		}
		
		
	}
	
	//search by email for now
	public int signIn(String email){
		try{
			Statement stat = con.createStatement();
			String query = "";
			query+="select * from USER where Email = '" + email + "';";
			
			ResultSet result = (ResultSet) stat.executeQuery(query);
			if(!result.next()){	//empty set
				return -1;
			}
			return 1;
			
		}catch(SQLException e){
			
		}
		
		
		return 0;
	}
	
}
