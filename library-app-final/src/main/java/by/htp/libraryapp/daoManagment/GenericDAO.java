package by.htp.libraryapp.daoManagment;

import java.sql.Connection;

public abstract class GenericDAO<T> {

	protected Connection connection;

	protected GenericDAO(Connection connection) {
		this.connection = connection;
	}
}
