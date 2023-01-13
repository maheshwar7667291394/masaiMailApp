package com.masaiemail.service;

import java.util.List;

import com.masaiemail.exception.EmailException;
import com.masaiemail.model.Messages;

public interface EmailService {
	public Messages sendEmail(String token,Messages messages) throws EmailException;
	public List<Messages> starAMail(String token,String emailId) throws EmailException;
	public Messages deleteAMailMessageBox(String token, String emailId) throws EmailException;

}
