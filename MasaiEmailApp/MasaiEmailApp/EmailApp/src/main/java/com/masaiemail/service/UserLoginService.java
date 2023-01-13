package com.masaiemail.service;

import java.util.List;

import com.masaiemail.dto.UserDto;
import com.masaiemail.dto.UserLoginDto;
import com.masaiemail.exception.UserException;
import com.masaiemail.model.LogginSession;
import com.masaiemail.model.Messages;
import com.masaiemail.model.User;

public interface UserLoginService {

	public User ragisterUser(UserDto userDto) throws UserException;
	
	public LogginSession loginSesstion(UserLoginDto userLoginDto) throws UserException;
	
	public User updateUser(UserDto userDto) throws UserException;
	
	public List<Messages> getAllEmails(String token) throws UserException;
	
	public List<Messages> getAllStarredEmails(String token) throws UserException;
	
}
