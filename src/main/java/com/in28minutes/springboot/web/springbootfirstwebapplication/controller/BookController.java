package com.in28minutes.springboot.web.springbootfirstwebapplication.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.web.springbootfirstwebapplication.model.Book;
import com.in28minutes.springboot.web.springbootfirstwebapplication.service.BookRepository;
import com.in28minutes.springboot.web.springbootfirstwebapplication.service.BookService;


@Controller
@SessionAttributes("name")
public class BookController {

	@Autowired
	BookRepository repository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/list-books", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("books", repository.findByUser(name));
		//model.put("todos", service.retrieveTodos(name));
		return "list-books";
	}
	
	@RequestMapping(value = "/borrowed-books", method = RequestMethod.GET)
	public String showBorrowedBooks(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("books", repository.findByUser(name));
		return "borrowed-books";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("book", new Book(0, getLoggedInUserName(model),
				"Default Desc", new Date(), false));
		return "book";
	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		repository.deleteById(id);
		//service.deleteTodo(id);
		return "redirect:/list-books";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Optional<Book> todo = repository.findById(id);
		//Todo todo = service.retrieveTodo(id);
		model.put("book", todo);
		return "book";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Book book,
			BindingResult result) {

		if (result.hasErrors()) {
			return "book";
		}

		book.setUser(getLoggedInUserName(model));

		repository.save(book);
		//service.updateTodo(book);

		return "redirect:/list-books";
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "book";
		}
		
		book.setUser(getLoggedInUserName(model));
		repository.save(book);
		
		/*
		 * service.addTodo(getLoggedInUserName(model), todo.getDesc(),
		 * todo.getTargetDate(), false);
		 */
		return "redirect:/list-books";
	}
}
