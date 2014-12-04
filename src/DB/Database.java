/**
 * 
 */
/**
 * @author mohamed
 *
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection connection = null;

	public static void startConnection() throws ClassNotFoundException,
			SQLException {

		Class.forName(Config.JDBC_DRIVER);

		System.out.println("Connecting to database...");
		connection = DriverManager.getConnection(
				Config.DB_URL + Config.DB_NAME, Config.USER, Config.PASS);
		System.out.println("Every thing good");

	}

	public static void closeConnection() throws SQLException {
		connection.close();
		System.out.println("Connection closed");
	}

	public static Connection getConnection() {
		return connection;
	}
}