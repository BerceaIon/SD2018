package com.bercea.assigment2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bercea.assigment2.handler.Handler;
import com.bercea.assigment2.handler.impl.DeleteBookHandler;
import com.bercea.assigment2.mediator.Mediator;
import com.bercea.assigment2.mediator.impl.MediatorImpl;

@Controller
public class BookCommandController {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
    private ModelMap modelMapper;
	
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
}	
