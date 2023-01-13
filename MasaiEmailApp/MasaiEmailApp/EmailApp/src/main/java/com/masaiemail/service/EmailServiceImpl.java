package com.masaiemail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masaiemail.exception.EmailException;
import com.masaiemail.model.LogginSession;
import com.masaiemail.model.Messages;
import com.masaiemail.model.User;
import com.masaiemail.repo.LogginSessionRepo;
import com.masaiemail.repo.MessagesRepo;
import com.masaiemail.repo.UserRepo;
import com.masaiemail.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private LogginSessionRepo logginSessionRepo;
	@Autowired 
	private UserRepo userRepo;
	@Autowired 
	private MessagesRepo messagesRepo;

	@Override
	public Messages sendEmail(String token, Messages messages) throws EmailException {
		LogginSession logginSession=this.checkPermisson(token);
		if(logginSession!=null) {
			Optional<User> usOptional= userRepo.findById(messages.getEmail());
			if(usOptional.isPresent()) {
				User user=usOptional.get();
				messages.setEmail(logginSession.getEmail());
				user.getMessages().add(messages);
				User user2= userRepo.save(user);
			List<Messages>messages2=user2.getMessages();
			return messages2.get(messages2.size()-1);
			}
			else {
				throw new EmailException("Entered Mail Does Not Exist");
			}
		}
		throw new EmailException("Please Enter Valid token");
	}

	@Override
	public List<Messages> starAMail(String token, String emailId) throws EmailException {
		LogginSession logginSession=this.checkPermisson(token);
		if(logginSession!=null) {
			Optional<User> usOptional= userRepo.findById(logginSession.getEmail());
			User user=usOptional.get();
			List<Messages> messages2=new ArrayList<>();
			List<Messages> messages=user.getMessages();
			for(int i=0;i<messages.size();i++) {
				if(messages.get(i).getEmail().equals(emailId)) {
					messages.get(i).setStart(true);
					messages2.add(messages.get(i));
				}
			}
			userRepo.save(user);
			return messages2;
			
		}
		throw new EmailException("U are not login");
	}

	@Override
	public Messages deleteAMailMessageBox(String token, String emailId) throws EmailException {
		LogginSession logginSession=this.checkPermisson(token);
		if(logginSession!=null) {
			Optional<User> usOptional= userRepo.findById(logginSession.getEmail());
			User user=usOptional.get();
			List<Messages> messages=user.getMessages();
			for(int i=0;i<messages.size();i++) {
				if(messages.get(i).getEmail().equals(emailId)) {
					Messages messages2=messages.remove(i);
					return messages2;
				}
			}
			userRepo.save(user);
		}
		throw new EmailException("U are not login");
		
	}
	
	
	// for permisson 
	
	public LogginSession checkPermisson(String key) {
		Optional<LogginSession> logOptional= logginSessionRepo.findByKey(key);
		if(logOptional.isPresent()) {
			return logOptional.get();
		}
		return null;
	}

}
