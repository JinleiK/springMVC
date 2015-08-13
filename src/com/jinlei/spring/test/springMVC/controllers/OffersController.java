package com.jinlei.spring.test.springMVC.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jinlei.spring.test.springMVC.dao.FormValidationGroup;
import com.jinlei.spring.test.springMVC.dao.Offer;
import com.jinlei.spring.test.springMVC.service.OffersService;

@Controller
public class OffersController {

	private OffersService offerService;

	// @RequestMapping("/")
	// public ModelAndView showHome() {
	// //session.setAttribute("name", "John");
	//
	// ModelAndView mv = new ModelAndView("home");
	//
	// Map<String, Object> model = mv.getModel();
	//
	// model.put("name", "Cat");
	//
	// return mv;
	// }

	@Autowired
	public void setOfferService(OffersService offerService) {
		this.offerService = offerService;
	}

	// .../test?id=123
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {

		System.out.println("Id is :" + id);
		return "home";
	}

	// @ExceptionHandler(DataAccessException.class)
	// public String HandleDatabaseException(DataAccessException ex) {
	// return "error";
	// }

	@RequestMapping("/offers")
	public String showOffers(Model model) {
		// offerService.throwTestException();

		List<Offer> offers = offerService.getCurrent();
		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {
		Offer offer = null;
		if (principal != null) {
			String username = principal.getName();
			offer = offerService.getOffer(username);
		}

		if (offer == null)
			offer = new Offer();
		model.addAttribute("offer", offer);
		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {
		if (result.hasErrors()) {
			// System.out.println("form does not validate.");
			// List<ObjectError> errors = result.getAllErrors();
			// for(ObjectError error : errors) {
			// System.out.println(error.getDefaultMessage());
			// }
			return "createoffer";
		}
		
		if(delete == null) {
			String username = principal.getName();

			offer.getUser().setUsername(username);

			offerService.save(offer);

			return "offercreated";
		} else {
			offerService.delete(offer.getId());
			return "offerdeleted";
		}

		
	}
}
