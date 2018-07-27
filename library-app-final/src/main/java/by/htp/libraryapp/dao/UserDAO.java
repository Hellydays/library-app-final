package by.htp.libraryapp.dao;

import java.util.List;

import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.User;

public interface UserDAO {
	
	User login();
	
	List<Book> getExpiredBooks(User user);
	
	void welcomeUser(User user);

}
