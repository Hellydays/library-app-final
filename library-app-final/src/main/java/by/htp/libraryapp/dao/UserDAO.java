package by.htp.libraryapp.dao;

import java.util.List;

import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.TakenBook;
import by.htp.libraryapp.entity.User;

public interface UserDAO {
	
	User login();
	
	List<Book> getExpiredBooks(User user);
	
	List<TakenBook> getTakenBooks(User user);
	
	void welcomeUser(User user);

}
