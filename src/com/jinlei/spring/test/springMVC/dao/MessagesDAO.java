package com.jinlei.spring.test.springMVC.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public MessagesDAO() {
		System.out.println("successfully loaded mesasgeDAO");
	}

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria ctr = session().createCriteria(Message.class);
		return ctr.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Criteria ctr = session().createCriteria(Message.class);
		ctr.createAlias("user", "u");
		ctr.add(Restrictions.eq("u.username", username));
		return ctr.list();
	}

	public Message getMessage(int id) {
		Criteria ctr = session().createCriteria(Message.class);
		ctr.add(Restrictions.idEq(id));
		return (Message) ctr.uniqueResult();
	}

	public void update(Message message) {
		session().update(message);
	}

	public void createOrUpdate(Message message) {	
		System.out.println(message);
		session().saveOrUpdate(message);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Message where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}
}
