package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import DB.tabels.User;

public class Table{

	public String table_name = null;
	public String primary_id = null;

	private ArrayList<String> columns = new ArrayList<String>();
	private HashMap<String, Object> column = new HashMap<String, Object>();
	Statement statement = null;

	private String select;
	private String where;
	private String lastQuery;
	private ArrayList<String> insertColumns = new ArrayList<String>();
	private ArrayList<Object> insertValues = new ArrayList<Object>();

	public Table(String table_name) {
		this.table_name = table_name;
		getColumnsNames();
	}

	public Table(String table_name, String primary_id) {
		this.table_name = table_name;
		this.primary_id = primary_id;
		getColumnsNames();
	}

	public ArrayList<String> getColumnsNames() {
		ResultSet results;
		try {
			results = executeQuery("SELECT * FROM " + table_name + " LIMIT 1");

			ResultSetMetaData metadata = results.getMetaData();

			int columnCount = metadata.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = metadata.getColumnName(i);
				columns.add(columnName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return columns;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		statement = Database.getConnection().createStatement();
		ResultSet results = statement.executeQuery(query);
		this.lastQuery = query;
		clearVariables();
		return results;

	}

	public void executeUpdate(String query) throws SQLException {
		statement = Database.getConnection().createStatement();
		statement.executeUpdate(query);
		this.lastQuery = query;
		clearVariables();
	}

	public ResultSet getAll() throws SQLException {
		return get();
	}

	private void clearVariables() {
		this.select = null;
		this.where = null;
		insertColumns.clear();
		insertValues.clear();
	}

	public ResultSet get() throws SQLException {
		String query = "SELECT ";
		if (this.select == null)
			this.select = "*";
		query += this.select + " FROM " + this.table_name + " ";
		if (this.where != null)
			query += "WHERE " + this.where;
		return executeQuery(query);

	}

	public void delete() throws SQLException {
		String query = "DELETE FROM " + this.table_name + " ";
		if (this.where != null)
			query += "WHERE " + this.where;
		executeUpdate(query);
	}

	public Table updateValue(String colName, Object val) {

		this.insertColumns.add(colName);
		this.insertValues.add(val);

		return this;
	}

	public void update() throws SQLException {
		String query = "UPDATE " + this.table_name + " SET ";

		for (int i = 0; i < insertColumns.size(); i++) {
			query += "`" + insertColumns.get(i) + "`" + " = "
					+ insertValues.get(i);
			if (insertColumns.size() != i + 1)
				query += ",";
		}
		query += " ";

		if (this.where != null)
			query += "WHERE " + this.where;
		executeUpdate(query);
	}

	public Table select() {
		this.select = null;
		return this;
	}

	public Table select(String colNames) {
		this.select = colNames;
		return this;
	}

	public Table where(String condition) {
		this.where = condition;
		return this;
	}

	public Table insertValue(String colName, Object val) {

		this.insertColumns.add(colName);
		this.insertValues.add(val);

		return this;
	}

	public void insert() throws SQLException {

		String query = "INSERT INTO " + table_name + " (";

		for (int i = 0; i < insertColumns.size(); i++) {
			query += "`" + insertColumns.get(i) + "`";
			if (insertColumns.size() != i + 1)
				query += ",";
		}
		query += ") VALUES ( ";
		for (int i = 0; i < insertValues.size(); i++) {
			query += "\'" + insertValues.get(i) + "\'";
			if (insertValues.size() != i + 1)
				query += " , ";
		}
		query += " )";

		executeUpdate(query);
	}

	public String getLastQuery() {
		return lastQuery;
	}

	public void find(Object id) throws SQLException {
		this.where = "id = "+id;
		ResultSet rs = this.get();
		if(rs.next())
			for(String col : columns){
				column.put(col, rs.getObject(col));
			}
	}
	
	public Object getCol(String col){
		return column.get(col);
	}
}
