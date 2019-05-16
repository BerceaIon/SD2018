package com.in28minutes.springboot.web.springbootfirstwebapplication.model;

import java.util.List;

public class User {
	private String userType;
	private int id;
	private String lastName;
	private String firstName;
	private String password;
	private List<Book> booksBorrowed;
	private double wallet;
	
	public User(String userType, String lastName, String firstName, String password, double wallet) {
		super();
		this.userType = userType;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.wallet = wallet;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public List<Book> getBooksBorrowed() {
		return booksBorrowed;
	}

	public void setBooksBorrowed(List<Book> booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}

	public double getWallet() {
		return wallet;
	}
	
	public String getPassword() {
		return password;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	
}
