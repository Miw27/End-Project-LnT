import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

	Connection con;
	
	ResultSet rs;
	PreparedStatement ps;

	public Connect() {
		try { 
			String url = "jdbc:mysql://localhost:3306/final_project_lnt";
			String username = "root";
			String password = "";

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getMenu() {
		try {
			ps = con.prepareStatement("select * from pt_pudding");
			rs = ps.executeQuery();

		} catch (Exception e) {
		}

		return rs;
	}

	public ResultSet insertMenu(int Stock, int Price, String Name, String ID) {
		try {
			ps = con.prepareStatement("insert into pt_pudding (`Stock`, `Price`, `Name`, `ID`) values (?,?,?,?)");
			ps.setInt(1, Stock);
			ps.setInt(2, Price);
			ps.setString(3, Name);
			ps.setString(4, ID);
			ps.execute();
		} catch (Exception e) {

		}

		return rs;
	}

	public ResultSet updateMenu(int Price, int Stock, String ID) {
		try {
			ps = con.prepareStatement("update pt_pudding set `Price` = (?), `Stock` = (?) where `ID` = (?)");
			ps.setInt(1, Price);
			ps.setInt(2, Stock);
			ps.setString(3, ID);
			ps.execute();

		} catch (Exception e) {

		}

		return rs;
	}
	
	public ResultSet deleteMenu(String ID) {
		try {
			ps = con.prepareStatement("delete from pt_pudding where `ID` = (?)");
			ps.setString(1, ID);
			ps.execute();
		} catch (Exception e) {
		
		}

		return rs;
	}
}