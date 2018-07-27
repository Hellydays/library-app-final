package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.htp.libraryapp.dao.BookDAO;
import by.htp.libraryapp.dao.LibrarianDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.Book;
import by.htp.libraryapp.entity.Librarian;
import by.htp.libraryapp.entity.User;

public class LibrarianDAOimpl extends GenericDAO<LibrarianDAOimpl> implements LibrarianDAO {

	private static final String SQL_SELECT_USER = "SELECT * FROM Librarian WHERE login = ? AND password = ?";
	private static final String SQL_SELECT_USERS = "SELECT * FROM Users";
	private static final String SQL_INSERT_USER = "INSERT INTO Users VALUES (?, ?, ?, ?, ?)";

	public LibrarianDAOimpl(Connection connection) {
		super(connection);
	}

	public Librarian getLibrarianData(String login, String password) {

		Librarian librarian = null;
		PreparedStatement ps;

		try {
			ps = connection.prepareStatement(SQL_SELECT_USER);
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

		List<User> listUsers = getAllReaders();
		User lastUser = listUsers.get(listUsers.size() - 1);
		int insertId = lastUser.getId() + 1;
		
		System.out.println("Please enter reader data");
		
		PreparedStatement ps;
		Scanner scanner = new Scanner(System.in);

		try {

			ps = connection.prepareStatement(SQL_INSERT_USER);
			ps.setInt(1, insertId);
			
			System.out.println("Ticket number:");
			ps.setString(2, scanner.nextLine());
			
			System.out.println("Password:");
			ps.setString(3, scanner.nextLine());
			
			System.out.println("Name:");
			ps.setString(4, scanner.nextLine());
			
			System.out.println("Surname");
			ps.setString(5, scanner.nextLine());

			ps.executeUpdate();

		} catch (SQLException e) {
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

}
