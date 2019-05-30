package com.bercea.assigment2.decorator;

import com.bercea.assigment2.model.Book;

public class BookDecorator implements Ownership{

	private Book book;
	
	public BookDecorator(Book book) {
		this.book = book;
	}
	
	@Override
	public String decorate() {
		return book.decorate();
	}

}
