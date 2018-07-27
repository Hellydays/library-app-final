package by.htp.libraryapp.dao.logic;

import java.sql.Connection;

import by.htp.libraryapp.daoManagment.GenericDAO;

public class MainMenuDAOFactory extends GenericDAO<MainMenuDAOFactory> {

	public MainMenuDAOFactory(Connection connection) {
		super(connection);
	}
	
	public void showMainMenu() {
		System.out.println("Hi! Welcome to the library! What would you like to do?");
	}

}
