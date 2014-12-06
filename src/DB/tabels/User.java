package DB.tabels;
import DB.Table;

public class User extends Table {

	private static String table_name = "users" ;
	private static String primary_id = "id" ;
	
	
	public User() {
		super(table_name,primary_id);		
	}

}