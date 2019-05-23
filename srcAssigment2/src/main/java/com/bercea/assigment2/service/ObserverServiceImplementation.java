package com.bercea.assigment2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.Observer;
import com.bercea.assigment2.repository.BookRepository;
import com.bercea.assigment2.repository.ObserverRepository;

@Service
@Transactional
public class ObserverServiceImplementation implements ObserverService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ObserverRepository observerRepository;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void notifyObservers(Book book) {
		for (Observer observer : book.getObservers()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(observer.getMail());
			message.setSubject("Book available");
			message.setText("Book " + book.getAuthor() + " is avaiable!");
			mailSender.send(message);
		}
		for (Observer observer : book.getObservers()) {
			observerRepository.delete(observer);
		}
	}

	@Override
	public void addObserver(Book book, Observer observer) {
		book.getObservers().add(observer);
		observer.setBook(book);
		observerRepository.save(observer);
	}

}
