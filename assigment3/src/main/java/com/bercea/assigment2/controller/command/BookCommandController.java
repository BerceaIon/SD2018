package com.bercea.assigment2.controller.command;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bercea.assigment2.handler.Handler;
import com.bercea.assigment2.handler.impl.AddNewBookHandler;
import com.bercea.assigment2.handler.impl.DeleteBookHandler;
import com.bercea.assigment2.mediator.Mediator;
import com.bercea.assigment2.mediator.impl.MediatorImpl;
import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.command.BookCommandDTO;

@Controller
public class BookCommandController {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Bean
	public ModelMapper modelMapper() {
	   ModelMapper modelMapper = new ModelMapper();
	   return modelMapper;
	}
	
	@DeleteMapping(value="delete/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		Mediator mediator = new MediatorImpl();
		Handler handler = new DeleteBookHandler();
		((DeleteBookHandler)handler).setId(id);
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		factory.autowireBean(handler);
		mediator.addHandler(handler);
		mediator.handle(handler.getCommand(), handler);
		return "redirect:/admin";
	}
	
	@PostMapping(value="/addBook")
	public String addBook(@Valid BookCommandDTO bookDTO) {
		Mediator mediator = new MediatorImpl();
		Handler handler = new AddNewBookHandler();
		((AddNewBookHandler)handler).setBook(convertToBook(bookDTO));
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		factory.autowireBean(handler);
		mediator.addHandler(handler);
		mediator.handle(handler.getCommand(), handler);
		
		return "redirect:/admin";
	}
	
	@GetMapping(value="/addBook")
	public String getAddBookPage() {
		
		return "add-book";
	}
	
	private Book convertToBook(BookCommandDTO bookDTO) {
		return modelMapper.map(bookDTO, Book.class);
	}
}	


