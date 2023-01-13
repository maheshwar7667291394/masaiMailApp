package com.masai.serviceInerface;

import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;

public interface FirItr {

	public FIR  fileFir (FIR driver)throws FirException,UserException;

}

