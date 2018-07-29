package by.htp.libraryapp.dao;

import java.util.List;

import by.htp.libraryapp.entity.Book;

public interface BookDAO {
	
	List<Book> getAllBooks();
	
	Book getBook(String title);
	
	void addBook();
	
	void printBooks(List<Book> listBooks);
	
	void printBook(Book book);
	
	void setIsTakenTrue(Book book);
	
	void setIsTakenFalse(Book book);
	
}
