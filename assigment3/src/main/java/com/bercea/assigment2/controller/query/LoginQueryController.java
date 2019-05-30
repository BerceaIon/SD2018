package com.bercea.assigment2.controller.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bercea.assigment2.model.Book;
import com.bercea.assigment2.model.User;
import com.bercea.assigment2.service.read.BookQueryService;

@Controller
public class LoginQueryController {

	@Autowired
	private BookQueryService bookQueryService;
	
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> getBser() {
		System.out.println("da");
		return (List<Book>) bookQueryService.findAll();
	}
	
	@GetMapping("/home")
	public ModelAndView getBook() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("books", (List<Book>) bookQueryService.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin-filter-author", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> filterByAuthor() {
		// return (List<Book>) bookService.retrieveBooks("Dan");
		return (List<Book>) bookQueryService.retrieveBooks("Bercea");
		// return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value = "/user-filter-author", method = RequestMethod.GET)
	@ModelAttribute("books")
	public List<Book> userFilterByAuthor() {
		// return (List<Book>) bookService.retrieveBooks("Dan");
		return (List<Book>) bookQueryService.retrieveBooks("Bercea");
		// return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
}
