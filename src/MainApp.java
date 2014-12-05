import java.sql.ResultSet;
import java.sql.SQLException;

import DB.*;
import DB.tabels.User;

public class MainApp {
	public static void main(String[] args) throws SQLException,	ClassNotFoundException {
		
		
		Database.startConnection();
		User u = new User();

		ResultSet r = DB.select().from("users").get();
		
		while (r.next()) {
			System.out.println("----> " + r.getInt(1) + "   "
					+ r.getString(2));
		}
		
		
		
		
		System.out.println("------------------");
		User user = new User();
		ResultSet rs = user.getAll();

		while (rs.next()) {
			System.out.println("----> " + rs.getInt(1) + "   "
					+ rs.getString(2));
		}

		Database.closeConnection();

	}

}