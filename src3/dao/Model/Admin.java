package dao.Model;

import java.math.BigDecimal;

public class Admin {	
	private int idAdmin;
	private String lastName;
	private String firstName;
	private String password;
	
	@Override
	public String toString() {
		return "Admin [idAdmin=" + idAdmin + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}
	public Admin(int id, String lastName, String firstName, String password) {
		super();
		this.idAdmin = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}
	public int getId() {
		return idAdmin;
	}
	public void setId(int id) {
		this.idAdmin = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
