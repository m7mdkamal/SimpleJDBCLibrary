SimpleJDBCLibrary
=================

Simple Java JDBC Library (under development)

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

###Select 
for single record use `user.get("user_id")`
    
    User user = new User();
    user.get(3);
    System.out.println(user.getCol("first_name"));

for result set:

    ResultSet rs = DB.select("id , username").from("users").where("id > 5").get();
		
###Insert 
    User user = new User();
    user.setCol("id",3);
    user.setCol("first_name","Mohamed");
    user.setCol("age",20);
    user.save();

###Update
    User user = new User();
    user.get(3);
    user.setCol("first_name","Ahmed");
    user.update();


###Delete     
    User user = new User();
    user.get(3);
    user.remove();

or

    DB.from("users").where("id > 5").delete();

###Running query
`DB.executeQuery(Query);` and `DB.executeUpdate(query);`	
	
	ResultSet rs = DB.executeQuery("SELECT * FROM users");
	 DB.executeUpdate("INSERT INTO users (`id`,`pass`) VALUES ( '551' , '51' )");
	
