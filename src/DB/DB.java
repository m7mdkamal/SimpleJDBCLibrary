package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DB {
	private final static DB DB = new DB();
	static Statement statement = null;

	private static String select;
	private static String from;
	private static String where;
	private static String lastQuery;

	public static ResultSet executeQuery(String query) throws SQLException {
		statement = Database.getConnection().createStatement();
		ResultSet results = statement.executeQuery(query);
		lastQuery = query;
		clearVariables();
		return results;

	}

	public static void executeUpdate(String query) throws SQLException {
		statement = Database.getConnection().createStatement();
		System.out.println(query);
		statement.executeUpdate(query);
		lastQuery = query;
		clearVariables();
	}

	public ResultSet getAll() throws SQLException {
		return get();
	}

	private static void clearVariables() {
		select = null;
		where = null;
		from = null;
	}

	public ResultSet get() throws SQLException {
		String query = "SELECT ";
		if (select == null)
			select = "*";
		query += select + " FROM " + from + " ";
		if (where != null)
			query += "WHERE " + where;
		return executeQuery(query);

	}

	public static void delete() throws SQLException {
		String query = "DELETE FROM " + from + " ";
		if (where != null)
			query += "WHERE " + where;
		executeUpdate(query);
	}

	public static DB select() {
		select = null;
		return DB;
	}

	public static DB from(String f) {
		from = f;
		return DB;
	}

	public static DB select(String colNames) {
		select = colNames;
		return null;
	}

	public static DB where(String condition) {
		where = condition;
		return null;
	}

	public static String getLastQuery() {
		return lastQuery;
	}

}
