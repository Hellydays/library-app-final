package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.LibrarianDAO;
import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.Librarian;
import by.htp.libraryapp.entity.TakenBook;
import by.htp.libraryapp.entity.User;

public class LibrarianDAOimpl extends GenericDAO<LibrarianDAOimpl> implements LibrarianDAO {

	private static final String SQL_SELECT_LIBRARIAN = "SELECT * FROM Librarian WHERE login = ? AND password = ?";
	private static final String SQL_SELECT_USERS = "SELECT * FROM Users";
	private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_USER = "SELECT * FROM Users WHERE ticketNumber = ?";
	private static final String SQL_INSERT_TAKEN = "INSERT INTO Taken_books VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_RETURN_DATE = "UPDATE Taken_books SET dateReturned = ? WHERE idBooks = ? AND idUsers = ?";

	UserDAO userDao = new UserDAOimpl(connection);
	BookDAO bookDao = new BookDAOimpl(connection);

	public LibrarianDAOimpl(Connection connection) {
		super(connection);
	}

	public Librarian getLibrarianData(String login, String password) {

		Librarian librarian = null;
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_LIBRARIAN);
			ps.setString(1, login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				librarian = new Librarian();
				librarian.setId(rs.getInt("idLibrarian"));
				librarian.setLogin(rs.getString("login"));
				librarian.setPassword(rs.getString("password"));
				librarian.setUsername(rs.getString("username"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return librarian;

	}

	@Override
	public Librarian login() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter librarian login");
		String login = scanner.nextLine();

		System.out.println("Please enter password");
		String password = scanner.nextLine();

		Librarian librarian = getLibrarianData(login, password);
		return librarian;
	}

	@Override
	public void addReader() {

		System.out.println("Please enter reader data");

		PreparedStatement ps;
		Scanner scanner = new Scanner(System.in);

		try {

			ps = connection.prepareStatement(SQL_INSERT_USER);
			ps.setNull(1, Types.INTEGER);

			System.out.println("Ticket number:");
			ps.setString(2, scanner.nextLine());

			System.out.println("Password:");
			ps.setString(3, scanner.nextLine());

			System.out.println("Name:");
			ps.setString(4, scanner.nextLine());

			System.out.println("Surname");
			ps.setString(5, scanner.nextLine());

			ps.executeUpdate();

			System.out.println("User is added");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Sorry, user with such ticket number already exists");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}

	public List<User> getAllReaders() {
		User user = null;
		List<User> listUsers = new ArrayList<>();

		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_USERS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("idUsers"));
				user.setTicketNumber(rs.getString("ticketNumber"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUsers;

	}

	@Override
	public void passBookToReader(User user, Book book) {

		List<Book> listExpiredBooks = userDao.getExpiredBooks(user);
		List<TakenBook> listAllTaken = userDao.getTakenBooks(user);

		if (book.getIsTaken() == false) {
			System.out.println("Book is passed to " + user.getName());
			insertTakenRecord(user, book);
			bookDao.setIsTakenTrue(book);

		} else if (listExpiredBooks.size() > 0 && listAllTaken.size() >= 3) {
			System.out.println("Cannot pass book, reader " + user.getName() + " already has at least one expired book");
			System.out.println("Moreover user already already took 3 books from the library and didn't give back");

		} else if (listExpiredBooks.size() > 0) {
			System.out.println("Cannot pass book, reader " + user.getName() + " already has at least one expired book");
			bookDao.printBooks(listExpiredBooks);

		} else if (listAllTaken.size() >= 3) {
			System.out.println(
					"Cannot pass book, reader " + user.getName() + " already took 3 books and didn't give back");
			for (TakenBook takenBook : listAllTaken) {
				System.out.println(takenBook.toString());
			}

		} else {
			System.out.println("Sorry this book is already taken by another reader");
		}

	}

	public void insertTakenRecord(User user, Book book) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		LocalDate todaysDate = LocalDate.now();
		String formattedString = todaysDate.format(formatter);

		PreparedStatement ps;

		Scanner scanner = new Scanner(System.in);

		try {

			ps = connection.prepareStatement(SQL_INSERT_TAKEN);

			ps.setNull(1, Types.INTEGER);
			ps.setInt(2, user.getId());
			ps.setInt(3, book.getId());
			ps.setString(4, formattedString);
			ps.setNull(5, Types.INTEGER);

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	public void updateIsTaken() {
		
	}

	@Override
	public User getReader(String ticketNumber) {
		User user = null;

		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_USER);
			ps.setString(1, ticketNumber);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("idUsers"));
				user.setTicketNumber(rs.getString("ticketNumber"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	@Override
	public void setBookAsReturned(User user, Book book) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		LocalDate todaysDate = LocalDate.now();
		String formattedString = todaysDate.format(formatter);

		PreparedStatement ps;

		Scanner scanner = new Scanner(System.in);

		try {

			ps = connection.prepareStatement(SQL_UPDATE_RETURN_DATE);

			ps.setString(1, formattedString);
			ps.setInt(2, book.getId());
			ps.setInt(3, user.getId());

			ps.executeUpdate();
			
			bookDao.setIsTakenFalse(book);

		} catch (SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}

}
