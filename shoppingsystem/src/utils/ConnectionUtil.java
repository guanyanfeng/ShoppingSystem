
package utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static {
		InputStream is = ConnectionUtil.class
				.getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			driver = p.getProperty("DRIVER");
			url = p.getProperty("URL");
			user = p.getProperty("USER");
			password = p.getProperty("PASSWORD");
			Class.forName(driver);
		} catch (IOException | ClassNotFoundException e) {
			e.getStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void close(Connection con, Statement stmt, ResultSet rs)
			throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
	}
	public static void close1(Connection con, Statement stmt, Statement stmt2)
			throws SQLException {
		if (stmt != null)
			stmt.close();
		if (stmt2 != null)
			stmt2.close();
		if (con != null)
			con.close();
	}

	public static void close(Connection con, Statement stmt)
			throws SQLException {
		close(con,stmt,null);
	}
	public static void close(Connection con)
			throws SQLException {
		close(con,null,null);
	}
}
