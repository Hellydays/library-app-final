package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.User;

public class UserDAOimpl extends GenericDAO<UserDAOimpl> implements UserDAO {
	
	public UserDAOimpl(Connection connection) {
		super(connection);
	}

	private static final String SQL_SELECT_USER = "SELECT * FROM Users WHERE ticketNumber = ? AND password = ?";

	public User getUserData(String ticketNumber, String password) {
		
		User user = null;
		PreparedStatement ps;
		
		try {
			ps = connection.prepareStatement(SQL_SELECT_USER);
			ps.setString(1, ticketNumber);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
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
		scanner.close();
		
		return user;
	}

}
