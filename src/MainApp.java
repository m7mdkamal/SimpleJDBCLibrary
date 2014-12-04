import java.sql.ResultSet;
import java.sql.SQLException;

import DB.*;
import DB.tabels.User;

public class MainApp {
	public static void main(String[] args) throws SQLException,	ClassNotFoundException {
		
		
		Database.startConnection();

		User user = new User();
		
		user.where("id = 2").delete();
	    
		user.insertValue("id",91)
		    .insertValue("pass","11111")
		    .insert();
		user.where("id=91")
			.updateValue("pass","22222")
			.update();
		ResultSet rs = user.getAll();

		while (rs.next()) {
			System.out.println("----> " + rs.getInt(1) + "   "
					+ rs.getString(2));
		}
		User user1 = new User();
		ResultSet rs1 = user1.select("id").where("id < 2").get();

		while (rs1.next()) {
			System.out.println("----> " + rs1.getInt(1) + "   ");
		}
		
		Database.closeConnection();

	}
}