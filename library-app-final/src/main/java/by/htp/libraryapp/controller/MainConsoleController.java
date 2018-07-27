package by.htp.libraryapp.controller;

import java.sql.Connection;
import java.util.List;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.dao.impl.BookDAOimpl;
import by.htp.libraryapp.dao.impl.UserDAOimpl;
import by.htp.libraryapp.dao.logic.MainMenuDAOFactory;
import by.htp.libraryapp.daoManagment.DAOManager;
import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.User;

public class MainConsoleController {

	public static void main(String[] args) {
		
		DAOManager daoManager = new DAOManager();
		Connection connection = daoManager.connect();
		
		UserDAO userDao = new UserDAOimpl(connection);
		BookDAO bookDao = new BookDAOimpl(connection);
		User user = userDao.login();
		
		MainMenuDAOFactory mainMenu = new MainMenuDAOFactory(connection);
		
		if (user != null) {
			
			List<Book> listExpiredBooks = userDao.getExpiredBooks(user);
			
			if(listExpiredBooks.size() > 0) {
				System.out.println("Hi " + user.getName() + "! Glad to see you again! By the way it's time to bring back next books: ");
				bookDao.printBooks(listExpiredBooks);
			}
			
			mainMenu.showMainMenu();
			
		} else {
			System.out.println("Sorry, ticket number or password is incorrect");
		}
		
		daoManager.closeConnection(connection);

	}

}
