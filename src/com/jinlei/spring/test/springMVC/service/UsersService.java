package com.jinlei.spring.test.springMVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.dao.UsersDAO;


@Service("usersService")
public class UsersService {
	
	private UsersDAO usersDao;
	
	@Autowired
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}



	public void create(User user) {
		// TODO Auto-generated method stub
		usersDao.create(user);
	}



	public boolean exists(String username) {
		// TODO Auto-generated method stub
		return usersDao.exists(username);
	}


	@Secured("ROLE_ADMIN")
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return usersDao.getAll();
	}


}
