package com.bercea.assigment2.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.BookOrder;
import com.bercea.assigment2.repository.BookOrderRepository;
import com.bercea.assigment2.service.read.BookQueryService;

@Service
@Transactional
public class BookQueryServiceImpl implements BookQueryService {
	@Autowired
	private BookOrderRepository bookOrderRepository;

	public static List<Book> books = new ArrayList<Book>();

	
	@Override
	public List<Book> retrieveBooks(String author) {
		List<Book> filteredBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				filteredBooks.add(book);
			}
		}
		return filteredBooks;
	}

	@Override
	public List<BookOrder> getOrdersForAnUserByEmail(String email) {
		return bookOrderRepository.findBooksByUserEmail(email);
	}

	@Override
	public BookOrder getBookOrderById(Integer id) {
		return bookOrderRepository.findById(id).get();
	}

	@Override
	public Iterable<Book> findAll() {
		return null;
	}
}
