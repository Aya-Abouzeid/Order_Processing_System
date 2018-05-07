package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSet;

public class DBMaster {

	private static Connection con;
	private Manager manager = null;
	private User LoggedIn;
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
					+"'"+uPass +" ',"
					+"'"+email +" ',"
					+"'"+fName +" ',"
					+"'"+lName +" ',"
					+"'"+address +" ' );";
			
			String query2 = "";
			query+="select UID from USER where UName = '" + uName + "' and Upass = '" + uPass+"';";
			
			ResultSet result = (ResultSet) stat.executeQuery(query);
			if(!result.next()){	//empty set , NO SUCH USER
				return -1;
			}
			int UID = result.getInt("UID");
			 LoggedIn = new User(UID);
			return stat.executeUpdate(query);
		}catch (SQLException e){
			
			return -1;
		}
		
		
	}
	
	//search by email for now
	public int signIn(String UserName , String pass) throws ClassNotFoundException{
		try{
			Statement stat = con.createStatement();
			String query = "";
			query+="select UID from USER where UName = '" + UserName + "' and Upass = '" + pass+"';";
			
			ResultSet result = (ResultSet) stat.executeQuery(query);
			if(!result.next()){	//empty set , NO SUCH USER
				return -1;
			}
			int UID = result.getInt("UID");
;
			String query2 = "";
			query2+="select MID from MANAGER where MID = '" + UID + "';";
			
			ResultSet result2 = (ResultSet) stat.executeQuery(query2);
			if(result2.next()){	//Manager
				int MID = result.getInt("MID");
				 LoggedIn = new Manager(MID);
			}
			 LoggedIn = new User(UID);
			return 1;
			
		}catch(SQLException e){
			
			return -2;
		}
		
	}
	public void updateInfo(String[] data) throws SQLException{
		LoggedIn.updateProfile(data);
	}
	
}
