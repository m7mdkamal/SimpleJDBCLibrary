SimpleJDBCLibaray 
=================

Simple Java JDBC Libaray (under development)

Configuration
-------------
Modify the configuration class `DB/Config.java` then add your own database tables in `DB/tables` package.
Here is an example

    public class User extends Table {
    
    	private static String table_name = "users_table";
    	private static String primary_key = "key";
    
    	public User() {
    		super(table_name, primary_key);
    	}
    
    }

Usage
-----
For starting the connecntion `Database.startConnection();` and for closing `Database.closeConnection();`

###Select single row 
use `user.get("user_id")`
    
    User user = new User();
    user.get(3);
    System.out.println(user.getCol("first_name"));

###Delete     
    User user = new User();
    user.get(3);
    user.delete();

###Insert 
    User user = new User();
    user.setCol("id",3);
    user.setCol("first_name","Mohamed");
    user.setCol("age",20);
    user.insert();

