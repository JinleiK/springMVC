package com.jinlei.spring.test.springMVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinlei.spring.test.springMVC.dao.Offer;
import com.jinlei.spring.test.springMVC.dao.OffersDAO;


@Service("offerService")
public class OffersService {
	
	private OffersDAO offerDao;
	
	@Autowired
	public void setOfferDao(OffersDAO offerDao) {
		this.offerDao = offerDao;
	}


	public List<Offer> getCurrent() {
		return offerDao.getOffers();
	}


	public void create(Offer offer) {
		// TODO Auto-generated method stub
		offerDao.createOrUpdate(offer);
	}


	public void throwTestException() {
		// TODO Auto-generated method stub
		offerDao.getOffer(999);
	}


	public boolean hasOffer(String name) {
		if(name == null)
			return false;
		
		List<Offer> offers = offerDao.getOffers(name);
		if(offers.size() == 0)
			return false;
		return true;
	}


	public Offer getOffer(String username) {
		if(username == null)
			return null;
		
		List<Offer> offers = offerDao.getOffers(username);
		if(offers.size() == 0)
			return null;
		return offers.get(0);
	}


	public void save(Offer offer) {
		offerDao.createOrUpdate(offer);
	}


	public void delete(int id) {
		offerDao.delete(id);
		
	}
}
