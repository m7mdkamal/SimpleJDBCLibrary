import java.sql.SQLException;

import DB.*;
import DB.tabels.User;

public class MainApp {
	public static void main(String[] args) throws SQLException,	ClassNotFoundException {
		
		
		Database.startConnection();
		
		User user = new User();
		user.setCol("id",5);
		user.setCol("pass","SAAS");
		user.insert();
		user.delete();
		Database.closeConnection();

	}

}