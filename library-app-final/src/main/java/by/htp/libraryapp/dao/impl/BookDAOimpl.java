package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;

public class BookDAOimpl extends GenericDAO<BookDAOimpl> implements BookDAO{

	public BookDAOimpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> listBook = new ArrayList<>();
		listBook.add(new Book());
		return listBook;
	}

}
