package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.dao.logic.MainMenuDAOFactory;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.TakenBook;
import by.htp.libraryapp.entity.User;

public class UserDAOimpl extends GenericDAO<UserDAOimpl> implements UserDAO {

	private static final String SQL_SELECT_USER = "SELECT * FROM Users WHERE ticketNumber = ? AND password = ?";
	private static final String SQL_SELECT_TAKEN_EXPIRED = "SELECT * FROM Books LEFT JOIN Taken_books on Books.idBooks = Taken_books.idBooks where idUsers = ? AND dateTaken <= NOW() - INTERVAL 30 DAY AND dateReturned IS NULL";
	private static final String SQL_SELECT_ALL_TAKEN = "SELECT * FROM Taken_books where idUsers = ? AND dateReturned IS NULL";
	
	public UserDAOimpl(Connection connection) {
		super(connection);
	}

	public User getUserData(String ticketNumber, String password) {

		User user = null;
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_USER);
			ps.setString(1, ticketNumber);
			ps.setString(2, password);

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
	public User login() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter your ticket number");
		String ticketNumber = scanner.nextLine();

		System.out.println("Please enter your password");
		String password = scanner.nextLine();

		User user = getUserData(ticketNumber, password);
		return user;
	}

	@Override
	public List<Book> getExpiredBooks(User user) {
		List<Book> listBook = new ArrayList<>();
		Book book = null;

		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_TAKEN_EXPIRED);
			ps.setInt(1, user.getId());

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
	public void welcomeUser(User user) {
		UserDAO userDao = new UserDAOimpl(connection);
		BookDAO bookDao = new BookDAOimpl(connection);
		
		MainMenuDAOFactory mainMenu = new MainMenuDAOFactory(connection);

		List<Book> listExpiredBooks = userDao.getExpiredBooks(user);

		if (listExpiredBooks.size() > 0) {
			System.out.println("Hi " + user.getName()
					+ "! Glad to see you again! By the way it's time to bring back next books: ");
			bookDao.printBooks(listExpiredBooks);
		}

		mainMenu.showMainMenu();
		
	}

	@Override
	public List<TakenBook> getTakenBooks(User user) {
			
		List<TakenBook> listTakenBook = new ArrayList<>();
			TakenBook takenBook = null;

			PreparedStatement ps;

			try {
				ps = connection.prepareStatement(SQL_SELECT_ALL_TAKEN);
				ps.setInt(1, user.getId());

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					takenBook = new TakenBook();
					takenBook.setId(rs.getInt("idTaken_books"));
					takenBook.setIdUsers(rs.getInt("idUsers"));
					takenBook.setIdBooks(rs.getInt("idBooks"));
					takenBook.setDateTaken(rs.getDate("dateTaken"));
					takenBook.setDateReturned(rs.getDate("dateReturned"));
					
					listTakenBook.add(takenBook);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return listTakenBook;
	}

}
