package com.masaiemail.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.masaiemail.dto.UserDto;
import com.masaiemail.dto.UserLoginDto;
import com.masaiemail.exception.UserException;
import com.masaiemail.model.LogginSession;
import com.masaiemail.model.Messages;
import com.masaiemail.model.User;
import com.masaiemail.repo.MessagesRepo;
import com.masaiemail.service.UserLoginService;

@RestController
public class UserController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	
	@PostMapping("/masaimail/ragister")
	public ResponseEntity<User> ragisterUser(@RequestBody UserDto userDto) throws UserException{
		return new ResponseEntity<User>(userLoginService.ragisterUser(userDto), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/masaimail/login")
	public ResponseEntity<LogginSession> loginUser(@RequestBody UserLoginDto userLoginDto) throws UserException{
		return new ResponseEntity<LogginSession>(userLoginService.loginSesstion(userLoginDto), HttpStatus.OK);
		
	}
	
	@GetMapping("/masaimail/mail")
	public ResponseEntity<List<Messages>> getAllEmails(@RequestHeader("token") String token) throws UserException{
		return new ResponseEntity<List<Messages>>(userLoginService.getAllEmails(token), HttpStatus.OK);
	}
	
	@GetMapping("/masaimail/starred")
	public ResponseEntity<List<Messages>> getAllStarredEmails(@RequestHeader("token") String token) throws UserException{
		return new ResponseEntity<List<Messages>>(userLoginService.getAllStarredEmails(token), HttpStatus.OK);
		
	}
	
	@PutMapping("/masaimail/user")
	public ResponseEntity<User> updateUserDetails(@RequestBody UserDto userDto) throws UserException{
		return new ResponseEntity<User>(userLoginService.updateUser(userDto), HttpStatus.OK);
	}
	
	
	

}
