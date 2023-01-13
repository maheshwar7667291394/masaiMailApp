package com.masaiemail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mesgId;
	
	private String subject;
	@Column(nullable = false)
	private String email;
	private String desc;
	private boolean star;
	
	public Messages() {
		super();
	}
	
	public Messages(Integer mesgId, String subject, String email, String desc, boolean start) {
		super();
		this.mesgId = mesgId;
		this.subject = subject;
		this.email = email;
		this.desc = desc;
		this.star = start;
	}
	
	public Integer getMesgId() {
		return mesgId;
	}
	public void setMesgId(Integer mesgId) {
		this.mesgId = mesgId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean getStart() {
		return star;
	}
	public void setStart(boolean start) {
		this.star = start;
	}
	
}
