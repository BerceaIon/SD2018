package com.bercea.assigment2.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bercea.assigment2.handler.Handler;
import com.bercea.assigment2.service.query.write.BookCommandService;

public class DeleteBookHandler implements Handler{

	@Autowired
	BookCommandService bookCommandService;
	
	private int id;
	
	@Override
	public void execute(String command) {
		bookCommandService.deleteBook(id);
	}

	@Override
	public String getCommand() {
		return "deleteBook";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
