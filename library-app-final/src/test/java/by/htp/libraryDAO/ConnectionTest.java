package by.htp.libraryDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionTest {
	
	@Test
	public void testConnection() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("db_config");
			
			String driver = rb.getString("db.driver");
			String login = rb.getString("db.login");
			String pass = rb.getString("db.pass");
			String url = rb.getString("db.url");
			
			
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, login, pass);
			Assert.assertNotNull(connection);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
