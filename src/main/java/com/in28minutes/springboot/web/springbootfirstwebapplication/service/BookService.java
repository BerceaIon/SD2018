package com.in28minutes.springboot.web.springbootfirstwebapplication.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.springbootfirstwebapplication.model.Book;

@Service
public class BookService {
    private static List<Book> books = new ArrayList<Book>();
    private static int bookCount = 3;

    static {
    	books.add(new Book(1, "Ionut", "Learn Spring MVC", new Date(),
                false));
    	books.add(new Book(2, "Ionut", "Learn Struts", new Date(), false));
    	books.add(new Book(3, "Ionut", "Learn Hibernate", new Date(),
                false));
    }

    public List<Book> retrieveBooks(String user) {
        List<Book> filteredBooks = new ArrayList<Book>();
        for (Book todo : books) {
            if (todo.getUser().equalsIgnoreCase(user)) {
            	filteredBooks.add(todo);
            }
        }
        return filteredBooks;
    }
    
    public Book retrieveBook(int id) {
        for (Book todo : books) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    public void updateBook(Book todo){
    		books.remove(todo);
    		books.add(todo);
    }

    public void addBook(String name, String desc, Date targetDate,
            boolean isDone) {
    	books.add(new Book(++bookCount, name, desc, targetDate, isDone));
    }

    public void deleteBook(int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}