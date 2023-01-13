package com.masai.serviceInerface;

import java.util.List;

import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;
import com.masai.model.User;

public interface UserItr {

	public User register(User user)throws UserException;
	public String Login(User user)throws UserException;
	
	public List<FIR> allFir()throws FirException,UserException;
	
	public String DeleteFir(Integer FirId)throws FirException,UserException;
	

}

