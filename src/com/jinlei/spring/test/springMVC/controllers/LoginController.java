package com.jinlei.spring.test.springMVC.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinlei.spring.test.springMVC.dao.FormValidationGroup;
import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.service.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	public UsersService getUsersService() {
		return usersService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
//		throw new AccessDeniedException("heelo");
		
		List<User> users = usersService.getAll();
		model.addAttribute("users", users);
		return "admin";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
//	@RequestMapping("/createaccount")
//	public String createAccount() {
//		return "accountcreated";
//	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {
		if(result.hasErrors()) {
//			System.out.println("form does not validate.");
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : errors) {
//				System.out.println(error.getDefaultMessage());
//			}
			return "newaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())) {
			System.out.println("caught duplicate username");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
//		try {
		usersService.create(user);
//		} catch (DuplicateKeyException e) {
//			// TODO: handle exception
//			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists");
//			return "newaccount";
//		}
		
		return "accountcreated";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(Model model) {
		//model.addAttribute("user", new User());
		return "loggedout";
	}
}
