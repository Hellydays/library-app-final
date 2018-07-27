package by.htp.libraryapp.daoManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DAOManager {
	
	protected Connection connection;

	public Connection connect() {
		connection = null;
		ResourceBundle rb = ResourceBundle.getBundle("db_config");

		String driver = rb.getString("db.driver");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		String url = rb.getString("db.url");

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, pass);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
