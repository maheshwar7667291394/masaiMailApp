package com.masaiemail.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.masaiemail.exception.EmailException;
import com.masaiemail.model.Messages;
import com.masaiemail.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/masaimail/starred/{id}")
	public ResponseEntity<List<Messages>> starToAMail(@RequestHeader("token") String token,@PathVariable("id") String id) throws EmailException{
		return new ResponseEntity<List<Messages>>(emailService.starAMail(token, id), HttpStatus.OK);
		
	}
	
	@PostMapping("/masaimail/mail")
	public ResponseEntity<Messages> sendMail(@RequestHeader("token") String token,@RequestBody Messages messages) throws EmailException{
		return new ResponseEntity<Messages>(emailService.sendEmail(token, messages), HttpStatus.OK);
		
	}

	@DeleteMapping("/masaimail/starred/{id}")
	public ResponseEntity<Messages> delteAMailFromMessageBox(@RequestHeader("token") String token,@PathVariable("id") String id) throws EmailException{
		return new ResponseEntity<Messages>(emailService.deleteAMailMessageBox(token, id), HttpStatus.OK);
		
	}
}
