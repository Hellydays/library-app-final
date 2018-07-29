package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;

public class BookDAOimpl extends GenericDAO<BookDAOimpl> implements BookDAO {

	private static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM Books";
	private static final String SQL_SELECT_ONE_BOOK = "SELECT * FROM Books WHERE title = ?";
	private static final String SQL_INSERT_BOOK = "INSERT INTO Books VALUES (?, ?, ?, ?)";
	private static final String SQL_UPDATE_BOOK = "UPDATE Books SET isTaken = ? WHERE title = ?";

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
				book.setIsTaken(rs.getBoolean("isTaken"));
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

	@Override
	public void addBook() {

		PreparedStatement ps;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter book data");

		try {
			ps = connection.prepareStatement(SQL_INSERT_BOOK);
			ps.setNull(1, Types.INTEGER);

			System.out.println("Book title:");
			ps.setString(2, scanner.nextLine());

			System.out.println("Book author:");
			ps.setString(3, scanner.nextLine());

			ps.setBoolean(4, false);

			ps.executeUpdate();

			System.out.println("Book is added");

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}

	@Override
	public void setIsTakenTrue(Book book) {

		PreparedStatement ps;
		Scanner scanner = new Scanner(System.in);

		try {
			ps = connection.prepareStatement(SQL_UPDATE_BOOK);
			ps.setBoolean(1, true);
			ps.setString(2, book.getTitle());

			ps.executeUpdate();

			System.out.println("Book is set to taken");

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
	}

	@Override
	public void setIsTakenFalse(Book book) {
		PreparedStatement ps;
		Scanner scanner = new Scanner(System.in);

		try {
			ps = connection.prepareStatement(SQL_UPDATE_BOOK);
			ps.setBoolean(1, false);
			ps.setString(2, book.getTitle());

			ps.executeUpdate();

			System.out.println("Book is set to returned");

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
		
	}



}
