package com.jinlei.spring.test.springMVC.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/jinlei/spring/test/springMVC/config/dao-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/config/security-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Autowired
	private UsersDAO usersDao;
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("aaaaa", "asdfg", true, "Alice", "ROLE_USER");
	private User user2 = new User("bbbbb", "sdfsdfs", true, "Bobbob", "ROLE_USER");
	private User user3 = new User("ccccc", "sdfsdfs", true, "Catcat", "ROLE_USER");
	private User user4 = new User("ddddd", "sdfsdfs", false, "David", "ROLE_USER");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from messages");
		jdbc.execute("delete from offer");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateandRetrieve() {
		usersDao.create(user1);
		List<User> users1 = usersDao.getAll();
		assertEquals("user should be stored in the database", 1, users1.size());
		
		assertEquals("retrieved should be matched", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		List<User> users2 = usersDao.getAll();
		assertEquals("should be 4 in the database", 4, users2.size());
	}

	@Test
	public void testUsers() {
		User user = new User("ccccc", "hello", true, "Catcat", "ROLE_USER");
		usersDao.create(user);
		
		List<User> users = usersDao.getAll();
		assertEquals("# of users should be one", 1, users.size());
		
		assertTrue("User should exist", usersDao.exists(user.getUsername()));
		assertFalse("User should not exist", usersDao.exists("sddd"));
		
		assertEquals("Users should be identical", user, users.get(0));
	}

}
