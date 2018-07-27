package by.htp.libraryapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import by.htp.libraryapp.dao.UserDAO;
import by.htp.libraryapp.daoManagment.GenericDAO;
import by.htp.libraryapp.entity.User;

public class UserDAOimpl extends GenericDAO<UserDAOimpl> implements UserDAO {
	
	public UserDAOimpl(Connection connection) {
		super(connection);
	}

	private static final String SQL_SELECT_USER = "SELECT * FROM Users WHERE ticketNumber = ? AND password = ?";

	@Override
	public User login(String ticketNumber, String password) {
		
		User user = new User(1, "a", "v", "c", "d");
		
		
		return user;
	}

}
