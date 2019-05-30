package com.bercea.assigment2.decorator;

import com.bercea.assigment2.model.Book;

public class BookBorrowed extends BookDecorator{

	public BookBorrowed(Book book) {
		super(book);
	}
	 public String decorate() {
		 return super.decorate()+"is borrowed";
	 }

}
