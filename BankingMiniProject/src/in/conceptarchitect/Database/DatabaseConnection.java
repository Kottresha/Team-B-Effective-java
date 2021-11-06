package in.conceptarchitect.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	static Connection conn;
	static Statement stmt;
	
	public DatabaseConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "Banking", "Banking");
			stmt = conn.createStatement();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertQuery(String sqlQuery) {
		try {
			stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateQuery(String sqlQuery) {
		try {
				stmt.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void selectQuery() {
	}
}