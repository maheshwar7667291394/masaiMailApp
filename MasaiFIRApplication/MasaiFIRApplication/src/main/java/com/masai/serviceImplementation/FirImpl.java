package com.masai.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.Repo.FirRepo;
import com.masai.Repo.LogRepo;
import com.masai.Repo.UserRepo;
import com.masai.exception.FirException;
import com.masai.exception.UserException;
import com.masai.model.FIR;
import com.masai.model.LogInOut;
import com.masai.model.User;
import com.masai.serviceInerface.FirItr;


@Service
public class FirImpl implements FirItr {

	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private LogRepo lr;
	
	@Autowired
	private FirRepo fr;

	@Override
	public FIR fileFir(FIR fir) throws FirException,UserException{
	
		
		
		
		
		List<LogInOut> lin=lr.findAll();
		if(lin.size()>0) {
			for(LogInOut l:lin) {
				
				Optional<User> u1=ur.findById(l.getId());
  
			    List<User> lf= fir.getFirMadeOn();
			    
			    boolean flag=true;
			    
			    for(User u:lf) {
			    	Optional<User> u2=ur.findById(u.getId());
			    	if(u2.isEmpty()) {
			    		flag=false;
                       break;			    		
			    	}
			    }
			    
			    if(flag) {
			    	
			      FIR fir2=fr.save(fir);
			      List<FIR> fl2 =u1.get().getFirFilled();
			      fl2.add(fir2);
			      u1.get().setFirFilled(fl2);
			      ur.save(u1.get());
			      
			      return fir2;
			      
			      
			    	
			    }else {
			    	throw new UserException("the users you are trying to file FIR on are not found");
			    }
				
			}
			throw new UserException("error");	
		}else {
			throw new UserException("You need to login First");
		}
		

	}
	
	
	
}
