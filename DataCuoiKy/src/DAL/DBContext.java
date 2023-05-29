package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	public Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/bienhongphat";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,"root","");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	private String account = "root";
	private String password = "";
}
