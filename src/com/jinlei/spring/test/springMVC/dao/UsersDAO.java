package com.jinlei.spring.test.springMVC.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDAO {

//	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;

	public UsersDAO() {
		System.out.println("successfully loaded usersDAO");
	}

//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

//	@Transactional
//	public boolean create(User user) {
//		MapSqlParameterSource params = new MapSqlParameterSource();
//
//		params.addValue("username", user.getUsername());
//		params.addValue("password", passwordEncoder.encode(user.getPassword()));
//		params.addValue("name", user.getName());
//		params.addValue("enabled", user.isEnabled());
//		params.addValue("authority", user.getAuthority());
//
//		return jdbc
//				.update("insert into users (username, password, name, enabled, authority) values (:username, :password, :name, :enabled, :authority)",
//						params) == 1;
//
//	}
	
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(String username) {
		Criteria crt = session().createCriteria(User.class);
		//crt.add(Restrictions.eq("username", username));
		crt.add(Restrictions.idEq(username));
		User user = (User) crt.uniqueResult();
		return user != null;
//		return jdbc.queryForObject(
//				"select count(*) from users where username=:username",
//				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return session().createQuery("from User").list();
//		return jdbc
//				.query("select * from users",
//						BeanPropertyRowMapper.newInstance(User.class));
	}

}
