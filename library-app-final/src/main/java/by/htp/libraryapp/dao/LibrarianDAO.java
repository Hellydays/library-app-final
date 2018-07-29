package by.htp.libraryapp.dao;

import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.Librarian;
import by.htp.libraryapp.entity.User;

public interface LibrarianDAO {
	
	Librarian login();
	
	void addReader();
	
	void passBookToReader(User user, Book book);
	
	User getReader(String tickerNumber);
	
	void setBookAsReturned(User user, Book book);

}
