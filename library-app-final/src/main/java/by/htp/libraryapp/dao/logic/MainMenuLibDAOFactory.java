package by.htp.libraryapp.dao.logic;

import java.sql.Connection;

import by.htp.libraryapp.dao.LibrarianDAO;
import by.htp.libraryapp.dao.impl.LibrarianDAOimpl;

public class MainMenuLibDAOFactory extends MainMenuDAOFactory {

	public MainMenuLibDAOFactory(Connection connection) {
		super(connection);
	}
	
	public void showMainLibrarianMenu() {
		System.out.println("Please select what would you like to do next: ");
		System.out.println(" - Type 1 to add new reader");
		System.out.println(" - Type 2 to (not implemented)");
		int choice = getChoice();
		selectionHandling(choice);
		
	}
	
	@Override
	public void selectionHandling(int choice) {
		LibrarianDAO librarian = new LibrarianDAOimpl(connection);
		
		switch(choice) {
		case 1:
			librarian.addReader();
			showMainLibrarianMenu();
			break;
			
		case 2:
			System.out.println("Someaction");
			break;
		}
			
	}

}
