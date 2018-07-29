package by.htp.libraryapp.entity;

import java.sql.Date;

public class TakenBook {
	
	private int id;
	private int idBooks;
	private int idUsers;
	private Date dateTaken;
	private Date dateReturned;
	
	public TakenBook() {
		
	}
	
	public TakenBook(int id, int idBooks, int idUsers, Date dateTaken, Date dateReturned) {
		super();
		this.id = id;
		this.idBooks = idBooks;
		this.idUsers = idUsers;
		this.dateTaken = dateTaken;
		this.dateReturned = dateReturned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBooks() {
		return idBooks;
	}

	public void setIdBooks(int idBooks) {
		this.idBooks = idBooks;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public Date getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(Date dateTaken) {
		this.dateTaken = dateTaken;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateReturned == null) ? 0 : dateReturned.hashCode());
		result = prime * result + ((dateTaken == null) ? 0 : dateTaken.hashCode());
		result = prime * result + id;
		result = prime * result + idBooks;
		result = prime * result + idUsers;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TakenBook other = (TakenBook) obj;
		if (dateReturned == null) {
			if (other.dateReturned != null)
				return false;
		} else if (!dateReturned.equals(other.dateReturned))
			return false;
		if (dateTaken == null) {
			if (other.dateTaken != null)
				return false;
		} else if (!dateTaken.equals(other.dateTaken))
			return false;
		if (id != other.id)
			return false;
		if (idBooks != other.idBooks)
			return false;
		if (idUsers != other.idUsers)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TakenBook [id=" + id + ", idBooks=" + idBooks + ", idUsers=" + idUsers + ", dateTaken=" + dateTaken
				+ ", dateReturned=" + dateReturned + "]";
	}
	

}
