package dao.Model;

public class Client {
	private int idClient;
	private String firstName;
	private String lastName;
	private int booksBorrowed;
	private String account;
	private String password;
	private int wallet;
	
	public Client(int idClient, String firstName, String lastName, int booksBorrowed, String account, String password, int wallet) {
		super();
		this.idClient = idClient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.booksBorrowed = booksBorrowed;
		this.account = account;
		this.password = password;
		this.wallet = wallet;
	}
	public Client(String firstName, String lastName, int booksBorrowed, String account, String password, int wallet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.booksBorrowed = booksBorrowed;
		this.account = account;
		this.password = password;
		this.wallet = wallet;
	}
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getBooksBorrowed() {
		return booksBorrowed;
	}
	public void setBooksBorrowed(int booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", booksBorrowed=" + booksBorrowed + ", account=" + account + ", wallet=" + wallet + "]";
	}


}
