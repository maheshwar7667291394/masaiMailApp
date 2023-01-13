package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;
import com.masai.serviceImplementation.FirImpl;

@RestController
public class FirController {

	@Autowired
	private FirImpl fi;
	
	@PostMapping("/masaifir/user/fir")
	public ResponseEntity<FIR> registerUser(@RequestBody FIR fir) throws FirException, UserException{
		FIR f= fi.fileFir(fir);
		return new ResponseEntity<>(f,HttpStatus.CREATED);
	}
	

	
}
