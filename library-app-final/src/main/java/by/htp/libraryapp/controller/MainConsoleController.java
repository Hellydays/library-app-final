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
		User user = userDao.login();
		
		MainMenuDAOFactory mainMenu = new MainMenuDAOFactory(connection);
		
//		BookDAO bookDao = new BookDAOimpl(connection);
//		List<Book> listBook = bookDao.getAllBooks();
		
		if (user != null) {
			
			System.out.println(user.toString());
			mainMenu.showMainMenu();
		} else {
			System.out.println("Sorry, ticket number or password is incorrect");
		}
		
//		if(listBook != null) {
//			for(Book book : listBook)
//				System.out.println(book.toString());
//		}

	}

}
