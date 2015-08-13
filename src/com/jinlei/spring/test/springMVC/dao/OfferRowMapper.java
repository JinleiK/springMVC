package com.jinlei.spring.test.springMVC.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setAuthority(rs.getString("authority"));
		user.setEnabled(true);
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		
		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setEmail(rs.getString("email"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);
		
		return offer;
	}

}
