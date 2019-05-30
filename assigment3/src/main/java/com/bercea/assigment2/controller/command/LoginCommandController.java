package com.bercea.assigment2.controller.command;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bercea.assigment2.model.User;
import com.bercea.assigment2.service.query.write.UserCommandService;

@Controller
public class LoginCommandController {

	
	@Autowired
	private UserCommandService userCommandService;
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please corect the errors in form!");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		// we will save the user is no errors
		else if (userCommandService.isUserAlreadyPresent(user)) {
			modelAndView.addObject("successMessage", "User already exists!");
		} else {
			userCommandService.saveUser(user);
			modelAndView.addObject("successMessage", "Registered successfull!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
