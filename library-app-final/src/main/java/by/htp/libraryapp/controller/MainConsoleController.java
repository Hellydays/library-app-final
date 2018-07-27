package by.htp.libraryapp.controller;

import java.sql.Connection;
import java.util.Scanner;

import by.htp.libraryapp.dao.LibrarianDAO;
import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.dao.impl.LibrarianDAOimpl;
import by.htp.libraryapp.dao.impl.UserDAOimpl;
import by.htp.libraryapp.daoManagment.DAOManager;
import by.htp.libraryapp.entity.Librarian;
import by.htp.libraryapp.entity.User;

public class MainConsoleController {

	public static void main(String[] args) {

		DAOManager daoManager = new DAOManager();
		Connection connection = daoManager.connect();
		
		System.out.println("Welcome to the Library");
		System.out.println(" - Click 1 to login as user");
		System.out.println(" - Click 2 to login as librarian");
		
		selectRole(getRoleChoice(), connection);
		
		daoManager.closeConnection(connection);

	}

	public static void selectRole(int choice, Connection connection) {
		
		UserDAO userDao = new UserDAOimpl(connection);
		LibrarianDAO libDao = new LibrarianDAOimpl(connection);
		
		switch (choice) {
		case 1:	
			User user = userDao.login();
			if (user != null) {
				userDao.welcomeUser(user);
			} else {
				System.out.println("Sorry, ticket number or password is incorrect");
			}
			break;
		case 2:
			Librarian librarian = libDao.login();
			if(librarian != null) {
				// libDao.addReader();

			} else {
				System.out.println("Sorry, login or password is incorrect");
			}
		}
	}

	public static int getRoleChoice() {
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		return choice;
	}

}
