package by.htp.libraryapp.dao.logic;

import java.sql.Connection;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.LibrarianDAO;
import by.htp.libraryapp.dao.impl.BookDAOimpl;
import by.htp.libraryapp.dao.impl.LibrarianDAOimpl;
import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.User;

public class MainMenuLibDAOFactory extends MainMenuDAOFactory {
	
	String ticketNumber;
	User user;
	Book book;
	
	public MainMenuLibDAOFactory(Connection connection) {
		super(connection);
	}
	
	public String getTicketNumber() {
		Scanner scanner = new Scanner(System.in);	
		String ticketNumber = scanner.nextLine();
		return ticketNumber;
		
	}

	public void showMainLibrarianMenu() {
		System.out.println("        ");
		System.out.println("Please select what would you like to do next: ");
		System.out.println(" - Type 1 to add new reader");
		System.out.println(" - Type 2 to add new book");
		System.out.println(" - Type 3 to pass book to reader");
		System.out.println(" - Type 4 to set book as returned by reader");
		int choice = getChoice();
		selectionHandling(choice);

	}

	@Override
	public void selectionHandling(int choice) {
		LibrarianDAO librarian = new LibrarianDAOimpl(connection);
		BookDAO bookDao = new BookDAOimpl(connection);

		switch (choice) {
		case 1:
			librarian.addReader();
			showMainLibrarianMenu();
			break;
		case 2:
			bookDao.addBook();
			showMainLibrarianMenu();
			break;
		case 3:
			System.out.println("Please enter reader's ticket number");
			
			ticketNumber = getTicketNumber();
			User user = librarian.getReader(ticketNumber);
			System.out.println(user.toString());
			
			System.out.println("Please enter the book title");
			Book book = bookDao.getBook(getBookTitle());
			
			librarian.passBookToReader(user, book);			
			showMainLibrarianMenu();
			
			break;
		case 4:
			System.out.println("Please enter reader's ticket number");
			ticketNumber = getTicketNumber();
			user = librarian.getReader(ticketNumber);
			System.out.println(user.toString());
			
			System.out.println("Please enter the book title");
			book = bookDao.getBook(getBookTitle());
			
			librarian.setBookAsReturned(user, book);
			
			break;
		}

	}

}
