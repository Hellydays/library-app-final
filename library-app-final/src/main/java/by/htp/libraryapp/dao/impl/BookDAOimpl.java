package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;

public class BookDAOimpl extends GenericDAO<BookDAOimpl> implements BookDAO {

	private static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM Books";
	private static final String SQL_SELECT_ONE_BOOK = "SELECT * FROM Books WHERE title = ?";

	public BookDAOimpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Book> getAllBooks() {

		List<Book> listBook = new ArrayList<>();
		Book book = null;

		try {
			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL_BOOKS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("idBooks"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				listBook.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listBook;
	}

	@Override
	public void printBooks(List<Book> listBook) {
		for (Book book : listBook)
			System.out.println(book.toString());
	}

	@Override
	public Book getBook(String title) {

		Book book = null;

		try {
			PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ONE_BOOK);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("idBooks"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public void printBook(Book book) {
		System.out.println(book.toString());
		
	}
	

}
