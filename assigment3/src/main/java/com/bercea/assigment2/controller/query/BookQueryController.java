package com.bercea.assigment2.controller.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bercea.assigment2.service.read.BookQueryService;

@Controller
@RequestMapping("/book")
public class BookQueryController {

	@Autowired
	private BookQueryService bookQueryService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String userPage(ModelMap modelMap) {
		modelMap.put("books", bookQueryService.findAll());
		return "home";
	}
}
