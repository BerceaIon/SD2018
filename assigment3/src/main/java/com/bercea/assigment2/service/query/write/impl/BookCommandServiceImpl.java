package com.bercea.assigment2.service.query.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.BookOrder;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.repository.BookOrderRepository;
import com.bercea.assigment2.repository.BookRepository;
import com.bercea.assigment2.service.query.write.BookCommandService;
import com.bercea.assigment2.service.query.write.UserCommandService;

@Service
@Transactional
public class BookCommandServiceImpl implements BookCommandService {

	@Autowired
	private UserCommandService userService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookOrderRepository bookOrderRepository;
	
	public static List<Book> books = new ArrayList<Book>();

	@Override
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public void borrowBook(Integer bookId, String userMail) {
		if (!bookRepository.findById(bookId).isPresent()) {
			return;
		}
		User user = userService.getUserByEmail(userMail);
		if (user == null) {
			return;
		}
		Book book = bookRepository.findById(bookId).get();
		System.out.println(book.isAvailable());
		if (book.isAvailable()) {
			BookOrder bookOrder = new BookOrder();
			bookOrder.setUser(user);
			bookOrder.setBook(book);
			book.setAvailable(false);
			bookOrderRepository.save(bookOrder);
		}
	}

	@Override
	public void sendBookBack(Integer bookOrderId) {
		BookOrder bookOrder = bookOrderRepository.findById(bookOrderId).get();
		Optional<Book> optionalBook = bookRepository.findById(bookOrder.getBook().getId());
		if (!optionalBook.isPresent()) {
			System.out.println("No book found!");
			return;
		}
		Book book = optionalBook.get();
		book.setAvailable(true);
		bookRepository.save(book);
		bookOrderRepository.delete(bookOrder);
	}
}
