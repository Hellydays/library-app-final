package by.htp.libraryapp.entity;

public class User {
	
	private int id;
	private String ticketNumber;
	private String password;
	private String name;
	private String surname;
	
	public User() {
		
	}
	
	public User(int id, String ticketNumber, String password, String name, String surname) {
		super();
		this.id = id;
		this.ticketNumber = ticketNumber;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ticketNumber=" + ticketNumber + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + "]";
	}
	
	
	
	
	

}
