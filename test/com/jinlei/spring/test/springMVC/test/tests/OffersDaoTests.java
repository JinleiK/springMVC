package com.jinlei.spring.test.springMVC.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.jinlei.spring.test.springMVC.dao.Offer;
import com.jinlei.spring.test.springMVC.dao.OffersDAO;
import com.jinlei.spring.test.springMVC.dao.User;
import com.jinlei.spring.test.springMVC.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/jinlei/spring/test/springMVC/config/dao-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/config/security-context.xml",
		"classpath:com/jinlei/spring/test/springMVC/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OffersDaoTests {

	@Autowired
	private UsersDAO usersDao;
	
	@Autowired
	private OffersDAO offersDao;
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("aaaaa", "asdfg", true, "Alice", "ROLE_USER");
	private User user2 = new User("bbbbb", "sdfsdfs", true, "Bobbob", "ROLE_USER");
	private User user3 = new User("ccccc", "sdfsdfs", true, "Catcat", "ROLE_USER");
	private User user4 = new User("ddddd", "sdfsdfs", false, "David", "ROLE_USER");
	
	private Offer offer1 = new Offer(user1, "aa@gmail.com", "lalalalala");
	private Offer offer2 = new Offer(user2, "bb@gmail.com", "hahahahaha");
	private Offer offer3 = new Offer(user2, "bbb@hotmail.com", "lalalalala");
	private Offer offer4 = new Offer(user3, "cc@gmail.com", "yoyoyoyoyo");
	private Offer offer5 = new Offer(user4, "dda@gmail.com", "tatatatatat");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from offer");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateOffer() {
		createTestObjects();
		
		List<Offer> offers = offersDao.getOffers();
		assertEquals("there should be 4 valid offers", 4, offers.size());
		
	}
	
	@Test
	public void testGetByUsername() {
		createTestObjects();
		
		List<Offer> offers1 = offersDao.getOffers(user2.getUsername());
		assertEquals("there should be 2 valid offers", 2, offers1.size());
		
		List<Offer> offers2 = offersDao.getOffers("sfsdf");
		assertEquals("there should be 0 valid offers", 0, offers2.size());
	}
	
	@Test
	public void testDelete() {
		createTestObjects();
		
		System.out.println("offer2 id: " + offer2.getId());
		
		offersDao.delete(offer2.getId());
		List<Offer> offers = offersDao.getOffers();
		assertEquals("there should be 3 offers in DB",3, offers.size());
		Offer retrived = offersDao.getOffer(offer2.getId());
		assertNull("should be no such offer", retrived);
		
		Offer retrivedExist = offersDao.getOffer(offer1.getId());
		assertNotNull("should be such offer", retrivedExist);
		
		Offer retrivedNotEnabled = offersDao.getOffer(offer5.getId());
		assertNull("should not be able to retrieve offer from not enabled user", retrivedNotEnabled);
	}
	
	@Test
	public void testOffers() {
		User user = new User("aaaaa", "hello", true, "Alice", "ROLE_USER");
		usersDao.create(user);
		
		Offer offer = new Offer(user, "alice@gmail.com", "sdfwewwwwwwwwwwwww");
		offersDao.createOrUpdate(offer);
		
		List<Offer> offers = offersDao.getOffers();
		assertEquals("there should be only one offer", 1, offers.size());
		
		assertEquals("retrieved offer should match the created offer", offer, offers.get(0));
		
		offer = offers.get(0);
		offer.setText("updated text ...");
		offersDao.createOrUpdate(offer);
		
		Offer updated = offersDao.getOffer(offer.getId());
		assertEquals("updated offers should be matched", offer, updated);
		
		offersDao.delete(offer.getId());
		List<Offer> empty = offersDao.getOffers();
		assertEquals("there should be no offers", 0, empty.size());
	}
	
	public void createTestObjects() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.createOrUpdate(offer1);
		offersDao.createOrUpdate(offer2);
		offersDao.createOrUpdate(offer3);
		offersDao.createOrUpdate(offer4);
		offersDao.createOrUpdate(offer5);
	}

}
