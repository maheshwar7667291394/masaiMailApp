package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;
import com.masai.model.User;
import com.masai.serviceImplementation.UserImpl;

@RestController
public class UserController {

	@Autowired
	private UserImpl ui;
	
	@PostMapping("/masaifir/user/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws  UserException{
		User u1= ui.register(user);
		return new ResponseEntity<>(u1,HttpStatus.CREATED);
	}
	
	@PostMapping("/masaifir/user/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) throws  UserException{
		String u1= ui.Login(user);
		return new ResponseEntity<>(u1,HttpStatus.OK);
	}
	@GetMapping("/masaifir/user/fir")
	public ResponseEntity<List<FIR>> allFIR() throws FirException, UserException{
		List<FIR> af= ui.allFir();
		return new ResponseEntity<>(af,HttpStatus.OK);
	}
	@PutMapping("/masaifir/user/fir/{firId}")
	public ResponseEntity<String> deleteFir(@PathVariable Integer firId ) throws FirException, UserException{
		String str=ui.DeleteFir(firId);
		return new ResponseEntity<>(str,HttpStatus.OK);
	}

	
	
	
}
