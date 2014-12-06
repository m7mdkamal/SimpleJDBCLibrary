import java.sql.ResultSet;
import java.sql.SQLException;

import DB.*;
import DB.tables.User;

public class MainApp {
	public static void main(String[] args) throws SQLException,	ClassNotFoundException {
		
		
		Database.startConnection();
		User u = new User();
		u.setCol("id", 551);
		u.setCol("pass", 51);
		u.save();
		Database.closeConnection();

	}

}