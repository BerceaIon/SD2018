package com.bercea.assigment2.service.read;

import java.util.List;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.BookOrder;

public interface BookQueryService {

	List<Book> retrieveBooks(String author);
		
	List<BookOrder> getOrdersForAnUserByEmail(String email);
	
	BookOrder getBookOrderById(Integer id);
}

