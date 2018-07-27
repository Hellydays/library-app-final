package by.htp.libraryapp.dao;

import java.util.List;

import by.htp.libraryapp.entity.Book;

public interface BookDAO {
	
	List<Book> getAllBooks();
	
	Book getBook(String title);
	
	void printBooks(List<Book> listBooks);
	
	void printBook(Book book);

}
