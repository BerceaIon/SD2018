package com.in28minutes.springboot.web.springbootfirstwebapplication.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.web.springbootfirstwebapplication.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findByUser(String user);
}
