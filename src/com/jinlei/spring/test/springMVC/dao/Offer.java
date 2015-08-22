package com.jinlei.spring.test.springMVC.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jinlei.spring.test.springMVC.validation.ValidEmail;

@Entity
@Table(name = "offer")
public class Offer {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@NotNull
	// @Pattern(regexp=".*\\@.*\\..*",
	// message="This does not appear to be an email address")
	@ValidEmail(min = 6, message = "This is not a valid email address", groups = {
			PersistenceValidationGroup.class, FormValidationGroup.class })
	private String email;
	@Size(min = 5, max = 255, message = "text must be between 5 and 255 characters", groups = {
			PersistenceValidationGroup.class, FormValidationGroup.class })
	@Column(name = "text")
	private String text;

	
	public Offer() {
		this.user = new User();
	}

	public Offer(User user, String email, String text) {
		this.user = user;
		this.email = email;
		this.text = text;
	}

	public Offer(int id, User user, String email, String text) {
		super();
		this.id = id;
		this.user = user;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return user.getUsername();
	}

	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", user=" + user + ", email=" + email
				+ ", text=" + text + ", attachPath=" + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


}
