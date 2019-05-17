package contacts;

import java.sql.*;


public class DB_connect {

		Connection conn = null;

		public ResultSet Connection(String query) {
			
			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=contacts_1;integratedSecurity=true"; // direct connect to database in url
			try {
				
				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
			        return rs;
				} 

			catch(SQLException ex) {
				System.out.println("Error in connection: " + ex.getMessage());
				return null;
			}
		}
		public int Connection1(String query)
		{
			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=contacts_1;integratedSecurity=true"; 
			
			try {
				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				int affectedrows = stmt.executeUpdate(query);
				
				
				return affectedrows;
				} 
			 
			catch(SQLException ex) {
				System.out.println("Error in connection: " + ex.getMessage());
				//ex.printStackTrace();
				return 0;
			}
		}
		
		public ResultSet Connection2(String query)
		{
			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=contacts_1;integratedSecurity=true"; 
			
			try {
				conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		        return rs;
				} 
			 
			catch(SQLException ex) {
				System.out.println("Error in connection: " + ex.getMessage());
				//ex.printStackTrace();
				return null;
			}
		}
	}

