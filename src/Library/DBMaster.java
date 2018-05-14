package Library;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class DBMaster {

	private static Connection con;
	private Manager manager = null;
	private User LoggedIn;
	private int UID;
	private int MID;
	private static Database db ;
	private static DBMaster dbm;
	private DBMaster() throws SQLException, ClassNotFoundException{
			
	}
	 
	public static DBMaster getDBMaster() throws ClassNotFoundException, SQLException{
		if(dbm == null){
			db = new Database();
			con = db.getCon();	
			return dbm = new DBMaster();
		}
		return dbm;		
	}
	
	//wrap it in userInfo bs b3d keda y3ny
	public int register(String uName,String uPass,
			String email,String fName,String lName,String address ) throws ClassNotFoundException{
		
		try {
			Statement stat = con.createStatement();
			String query = "";

			query+="insert into USER (UName,UPass,Email,FName,LName,ShippingAddress)"
					+ "values ( '"+uName +" ',"
					+"MD5('"+uPass +"'),"
					+"'"+email +" ',"
					+"'"+fName +" ',"
					+"'"+lName +" ',"
					+"'"+address +" ' );";
			
			int returnValue = stat.executeUpdate(query);
			String query2 = "";
			query2+="select UID from USER where UName = '" + uName + "' and Upass = MD5('" + uPass+"');";
			
			ResultSet result = (ResultSet) stat.executeQuery(query2);
			
			if(!result.next()){	//empty set , NO SUCH USER
				return -1;
			}
			 UID = result.getInt("UID");
			 LoggedIn = new User(UID);
			return 1;
		}catch (SQLException e){
			
			return -1;
		}
		
		
	}
	
	//search by email for now
	public int signIn(String UserName , String pass) throws ClassNotFoundException{
		try{
			Statement stat = con.createStatement();
			String query = "";
			query+="select UID from USER where UName = '" + UserName + "' and Upass = MD5('" + pass+"');";
			
			ResultSet result = (ResultSet) stat.executeQuery(query);
			if(!result.next()){	//empty set , NO SUCH USER
				return -1;
			}
			 UID = result.getInt("UID");
			String query2 = "";
			query2+="select MID from MANAGER where MID = " + String.valueOf(UID) + ";";
			ResultSet result2 = (ResultSet) stat.executeQuery(query2);

			if(result2.next()){	//Manager

				 MID = result2.getInt("MID");

				 LoggedIn = new Manager(MID);
				 return 2;
			}

			 LoggedIn = new User(UID);
			return 1;
			
		}catch(SQLException e){
			
			return -2;
		}
		
	}
	public int removeFromCart(String isbn) throws SQLException{
		return LoggedIn.removeFromCart(isbn);
	}
	public void logOut() throws SQLException{
		LoggedIn.logout(UID);
		LoggedIn = null;
	}
	public void updateInfo(String[] data) throws SQLException{
		LoggedIn.updateProfile(data);
	}
	public void addBook(String[] data) throws SQLException{
		System.out.println("heere 11");

		((Manager) LoggedIn).addBook(new Book(
				data[2],data[0],Integer.valueOf(data[1]),Integer.valueOf(data[7]),
				Double.valueOf(data[4]),Integer.valueOf(data[6]),data[5],String.valueOf(data[3])
						)
				);
	}
	public void modifyBook(String[] data) throws SQLException{
		((Manager) LoggedIn).updateBook(data
						);
				
	}
	public ResultSet searchBook(String[] data) throws SQLException{
		return (ResultSet) LoggedIn.searchBook(data);
	}
	public double getTotalPrice() throws SQLException{
		return  LoggedIn.getTotalPrice(UID);
	}
	public double getItemPrice(String isbn) throws SQLException{
		return LoggedIn.getItemPrice(isbn);
	}
}
