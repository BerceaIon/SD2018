package com.bercea.assigment2.service;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.Observer;

public interface ObserverService {
	void notifyObservers(Book book);

	void addObserver(Book book, Observer observer);
}
