package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection conn = null;

	public static Connection getConnection()  {
		if (conn == null) {
			try {
				Properties props = loadpProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbExeption(e.getMessage());
			}
		}

		return conn;
	}

	public static void closeConection() {
		if (conn != null) {
			try {
				conn.close();
			
		}catch (Exception e) {
			throw new DbExeption(e.getMessage());
		}}

	}

	private static Properties loadpProperties() {
		try (FileInputStream fs = new FileInputStream("db.propeties")) {
			Properties pros = new Properties();
			pros.load(fs);
			return pros;

		} catch (Exception e) {
			throw new DbExeption(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st ) {
		if(st != null) {
			
			try {
				st.close();
			} catch (SQLException e) {
				
			throw new DbExeption(e.getMessage());
			}
		}
		
	}
	public static void closeResultSet(ResultSet rs ) {
		if(rs != null) {
			
			try {
				rs.close();
			} catch (SQLException e) {
				
			throw new DbExeption(e.getMessage());
			}
		}
		
	}
	
}
