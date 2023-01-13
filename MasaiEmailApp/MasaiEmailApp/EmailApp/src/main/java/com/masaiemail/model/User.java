package com.masaiemail.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@Email(message = "please Enter Valid email")
	private String emailId;
	
	private String firstName;
	
	private String lastName;
	
	@Size(min = 10,message = "please enter valie messges")
	private String mobileNumber;
	
	private String password;
	
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Messages> messages=new ArrayList<>();
	
	public User() {
		super();
	}
	
	public User(String emailId, String firstName, String lastName, String mobileNumber, String password,
			LocalDate dateOfBirth, List<Messages> messages) {
		super();
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.messages = messages;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Messages> getMessages() {
		return messages;
	}
	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}
	
	

}
