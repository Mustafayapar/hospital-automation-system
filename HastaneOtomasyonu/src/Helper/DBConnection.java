package Helper;
import java.sql.*;

public class DBConnection {
	Connection con = null;
	
	public DBConnection() {}
	
	public Connection ConnDB() {
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&password=12345");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
