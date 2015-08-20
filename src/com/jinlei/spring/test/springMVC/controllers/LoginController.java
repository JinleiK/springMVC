package com.jinlei.spring.test.springMVC.controllers;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinlei.spring.test.springMVC.dao.FormValidationGroup;
import com.jinlei.spring.test.springMVC.dao.Message;
import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.service.UsersService;

@Controller
public class LoginController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private MailSender mailSender;
	
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
	
	@RequestMapping(value="/getmessages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		List<Message> messages = null;
		if(principal == null) {
			messages = new ArrayList<Message>();
		} else {
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		return data;
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}
	
	
	@RequestMapping(value="/sendmessage", method=RequestMethod.POST, headers =
		{"Content-type=application/json"}, produces="application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {
		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");
		
		System.out.println(text + "," + name + "," + email);
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("idiotcallie@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: " + name);
		mail.setText(text);
		
		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("can't send message");
		}
		
		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("target", target);
		rval.put("success", true);
		return rval;
	}
}
