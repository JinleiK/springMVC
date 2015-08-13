package com.jinlei.spring.test.springMVC.test.tests;

import static org.junit.Assert.assertEquals;

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

import com.jinlei.spring.test.springMVC.dao.Message;
import com.jinlei.spring.test.springMVC.dao.MessagesDAO;
import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.dao.UsersDAO;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/jinlei/spring/test/springMVC/config/dao-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/config/security-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDaoTests {

	@Autowired
	private UsersDAO usersDao;
	
	@Autowired
	private MessagesDAO messagesDao;
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("aaaaa", "asdfg", true, "Alice", "ROLE_USER");
	private User user2 = new User("bbbbb", "sdfsdfs", true, "Bobbob", "ROLE_USER");
	private User user3 = new User("ccccc", "sdfsdfs", true, "Catcat", "ROLE_USER");
	private User user4 = new User("ddddd", "sdfsdfs", false, "David", "ROLE_USER");
	
	private Message message1 = new Message("test subject 1", "test content 1", "Henry", "henry@gmail.com", user1);
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from messages");
		jdbc.execute("delete from offer");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreate() {
		createTestObjects();
		List<Message> messages = messagesDao.getMessages();
		assertEquals("should be one message in the DB", 1, messages.size());
	}
	
	public void createTestObjects() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		messagesDao.createOrUpdate(message1);
	}

}
