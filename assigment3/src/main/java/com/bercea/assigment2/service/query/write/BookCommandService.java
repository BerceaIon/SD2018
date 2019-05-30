package com.bercea.assigment2.service.query.write;

import com.bercea.assigment2.model.Book;

public interface BookCommandService {

	void addBook(Book book);
	
	void deleteBook(int id);
	
	void borrowBook(Integer bookId, String userMail);
	
	void sendBookBack(Integer bookOrderId);
}
