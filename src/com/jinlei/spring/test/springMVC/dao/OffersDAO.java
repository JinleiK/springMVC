package com.jinlei.spring.test.springMVC.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offersDao")
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private SessionFactory sessionFactory;

	public OffersDAO() {
		System.out.println("successfully loaded offerDAO");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		Criteria ctr = session().createCriteria(Offer.class);
		ctr.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return ctr.list();
//		return jdbc
//				.query("select * from offer, users where offer.username = users.username and users.enabled = true",
//						new OfferRowMapper());
	}

	public Offer getOffer(int id) {
		Criteria ctr = session().createCriteria(Offer.class);
		ctr.createAlias("user", "u");
		ctr.add(Restrictions.eq("u.enabled", true));
		ctr.add(Restrictions.idEq(id));
		return (Offer) ctr.uniqueResult();
//		MapSqlParameterSource params = new MapSqlParameterSource();
//		params.addValue("id", id);
//
//		return jdbc
//				.queryForObject(
//						"select * from offer, users where offer.id = :id and offer.username = users.username",
//						params, new OfferRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {
		Criteria ctr = session().createCriteria(Offer.class);
		ctr.createAlias("user", "u");
		ctr.add(Restrictions.eq("u.enabled", true));
		ctr.add(Restrictions.eq("u.username", username));
		return ctr.list();
//		return jdbc
//				.query("select * from offer, users where offer.username = users.username and users.username = :username",
//						new MapSqlParameterSource("username", username),
//						new OfferRowMapper());
	}

	public void update(Offer offer) {
		session().update(offer);
		
//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
//				offer);
//
//		return jdbc.update("update offer set text = :text where id = :id",
//				params) == 1;
	}

	public void createOrUpdate(Offer offer) {
		
		session().saveOrUpdate(offer);
//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
//				offer);
//
//		return jdbc
//				.update("insert into offer (username, text, email) values (:username, :text, :email)",
//						params) == 1;
	}

	@Transactional
	public int[] create(List<Offer> offers) {
		SqlParameterSource[] params = SqlParameterSourceUtils
				.createBatch(offers.toArray());
		return jdbc
				.batchUpdate(
						"insert into offer (username, text, email) values (:username, :text, :email)",
						params);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Offer where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
//		MapSqlParameterSource paramSource = new MapSqlParameterSource("id", id);
//		return jdbc.update("delete from offer where id = :id", paramSource) == 1;
	}
}
