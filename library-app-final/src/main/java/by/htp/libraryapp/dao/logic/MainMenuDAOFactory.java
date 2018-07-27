package by.htp.libraryapp.dao.logic;

import java.sql.Connection;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.impl.BookDAOimpl;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;

public class MainMenuDAOFactory extends GenericDAO<MainMenuDAOFactory> {

	public MainMenuDAOFactory(Connection connection) {
		super(connection);
	}
	
	public void showMainMenu() {
		System.out.println("Please select what would you like to do?");
		System.out.println(" - Type 1 to see all the available books");
		System.out.println(" - Type 2 to search for a certain book");
		int choice = getChoice();
		selectionHandling(choice);
		
	}
	
	public int getChoice() {
		Scanner scanner = new Scanner(System.in);	
		int choice = scanner.nextInt();
		return choice;
	
	}
	
	public String getBookTitle() {
		Scanner scanner = new Scanner(System.in);	
		String title = scanner.nextLine();
		scanner.close();
		return title;
		
	}
	
	public void selectionHandling(int choice) {
		
		BookDAO bookDao = new BookDAOimpl(connection);
		
		switch(choice) {
		case 1: 
			System.out.println(" *** Books catalog *** ");
			bookDao.printBooks(bookDao.getAllBooks());
			break;
		case 2:
			System.out.println("Please enter the book title");
			String title = getBookTitle();
			Book book = bookDao.getBook(title);
			if(book!=null) {
				bookDao.printBook(book);
			} else {
				System.out.println("Sorry, there is no book with such title");
			}
				
		}
	}
	

}
