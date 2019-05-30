package com.bercea.assigment2.decorator;

import com.bercea.assigment2.model.Book;

public class BookUnavailable extends BookDecorator{

	public BookUnavailable(Book book) {
		super(book);
	}
	
	public String decorate() {
	    return super.decorate()+"unavailable";
	}
}
