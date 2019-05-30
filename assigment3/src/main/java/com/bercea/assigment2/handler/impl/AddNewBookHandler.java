package com.bercea.assigment2.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bercea.assigment2.handler.Handler;
import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.service.query.write.BookCommandService;

public class AddNewBookHandler implements Handler {

	@Autowired
	BookCommandService bookCommandService;
	
	Book book;
	
	@Override
	public void execute(String command) {
		bookCommandService.addBook(getBook());
	}

	@Override
	public String getCommand() {
		return "addNewBook";
	}

	public Book getBook() {
		return book;
	}
}
