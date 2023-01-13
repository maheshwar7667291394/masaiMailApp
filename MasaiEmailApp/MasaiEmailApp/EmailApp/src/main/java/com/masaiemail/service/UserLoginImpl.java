package com.masaiemail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.aspectj.bridge.Message;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.masaiemail.dto.UserDto;
import com.masaiemail.dto.UserLoginDto;
import com.masaiemail.exception.UserException;
import com.masaiemail.model.LogginSession;
import com.masaiemail.model.Messages;
import com.masaiemail.model.User;
import com.masaiemail.repo.LogginSessionRepo;
import com.masaiemail.repo.UserRepo;
import com.masaiemail.service.UserLoginService;
import net.bytebuddy.utility.RandomString;

@Repository
public class UserLoginImpl implements UserLoginService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LogginSessionRepo logginSessionRepo;

	@Override
	public User ragisterUser(UserDto userDto) throws UserException {
		User user=new User();
		user.setEmailId(userDto.getEmailId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setPassword(userDto.getPassword());
		return userRepo.save(user);
	}

	@Override
	public LogginSession loginSesstion(UserLoginDto userLoginDto) throws UserException {
		Optional<User> usOptiona= userRepo.findById(userLoginDto.getEmailId());
		if(usOptiona.isPresent() && usOptiona.get().getPassword().equals(userLoginDto.getPassword())) {
			Optional<LogginSession> logOptional= logginSessionRepo.findByEmail(userLoginDto.getEmailId());
			if(logOptional.isEmpty()) {
				LogginSession logginSession=new LogginSession();
				String ukey  = RandomString.make(6);
				logginSession.setEmail(userLoginDto.getEmailId());
				logginSession.setKey(ukey);
				logginSession.setPassword(userLoginDto.getPassword());
				return logginSessionRepo.save(logginSession);
			}
			else {
				throw new UserException("u are already loged in");
			}
		}
		throw new UserException("Please Entere Valid Details");
		
		
		
	}

	@Override
	public User updateUser(UserDto userDto) throws UserException {
		Optional<User> usOptiona= userRepo.findById(userDto.getEmailId());
		if(usOptiona.isPresent()) {
			User user=usOptiona.get();
			user.setFirstName(userDto.getFirstName());
			user.setLastName(user.getLastName());
			user.setMobileNumber(userDto.getMobileNumber());
			user.setPassword(userDto.getPassword());
			return userRepo.save(user);
		}
		throw new UserException("Please Enter Valid Details");
	}

	@Override
	public List<Messages> getAllEmails(String token) throws UserException {
		LogginSession logginSession=this.checkPermisson(token);
		if(logginSession!=null) {
			List<Messages> messages= userRepo.findById(logginSession.getEmail()).get().getMessages();
			return messages;
		}
		throw new UserException("Please Login");
	}

	
	@Override
	public List<Messages> getAllStarredEmails(String token) throws UserException {
		LogginSession logginSession=this.checkPermisson(token);
		if(logginSession!=null) {
			List<Messages> messages= userRepo.findById(logginSession.getEmail()).get().getMessages();
			List<Messages> messages2=new ArrayList<>();
			for(Messages i:messages) {
				if(i.getStart()==true) {
					messages2.add(i);
				}
			}
			return messages2;
		}
		throw new UserException("Please Login");
	}
	
	
	public LogginSession checkPermisson(String key) {
		Optional<LogginSession> logOptional= logginSessionRepo.findByKey(key);
		if(logOptional.isPresent()) {
			return logOptional.get();
		}
		return null;
	}

	}
