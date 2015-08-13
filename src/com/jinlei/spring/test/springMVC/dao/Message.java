package com.jinlei.spring.test.springMVC.dao;

import java.io.Serializable;

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
@Table(name="messages")
public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582511668875100698L;

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=5, max=100)
	private String subject;
	@Size(min=5, max=1000)
	private String content;
	
	// name of the sender
	@Size(min=5, max=60)
	private String name;
	
	// email of the sender
	@ValidEmail
	private String email;
	
	// user of the receiver
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	public Message() {
		this.user = new User();
//		this.subject = "subject goes here";
//		this.content = "content goes here";
//		this.name = "sender's name";
//		this.email = "sender's email";
	}
	
	public Message(String subject, String content, String name, String email,
			User user) {
		super();
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", content="
				+ content + ", name=" + name + ", email=" + email
				+ ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}
