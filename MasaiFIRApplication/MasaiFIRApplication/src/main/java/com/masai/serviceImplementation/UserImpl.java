package com.masai.serviceImplementation;

import java.time.LocalDateTime;
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
import com.masai.serviceInerface.UserItr;

@Service
public class UserImpl implements UserItr {

	@Autowired
	private UserRepo ur;
	
	@Autowired
	private LogRepo lr;
	
	@Autowired
	private FirRepo fr;
	

	@Override
	public User register(User user) throws UserException {

		User u1=ur.findUseByMob(user.getMobileNumber());
		if(u1==null) {
			
			User u2=ur.save(user);
			return u2;
			
		}else {
			throw new UserException("A user is already registered with this Mobile Number");
		}
		
		
	}

	@Override
	public String Login(User user) throws UserException {
		
		List<LogInOut> lin= lr.findAll();
		if(lin.size()>0) {
			throw new UserException("A user is already Logged In");
		}else {
			
//			boolean flag=true;
			List<User> userList= ur.findAll();
			
			for(User u:userList) {
				if(u.getMobileNumber().equals(user.getMobileNumber()) && u.getPassword().equals(user.getPassword())){
					LogInOut lin2= new LogInOut();
					lin2.setUserMob(u.getMobileNumber());
					lin2.setPresent(true);
					lr.save(lin2);
					return "Login Successful";
				}
			}
			
			throw new UserException("No user found for supplied Mobile Number and Passwprd");
			
		}
		
	}

	@Override
	public List<FIR> allFir() throws FirException,UserException {
		
		List<LogInOut> lin=lr.findAll();
		if(lin.size()>0) {
			for(LogInOut l:lin) {
				
				Optional<User> u1=ur.findById(l.getId());
				
				
				if(u1.get().getFirFilled().size()>0) {
					return u1.get().getFirFilled();
				}else {
					throw new FirException("No FIR has been made");	
				}
				
			}
			throw new UserException("error");	
		}else {
			throw new UserException("You need to login First");
		}
	}

	@Override
	public String DeleteFir(Integer FirId) throws FirException, UserException {
		// TODO Auto-generated method stub
		
		
		List<LogInOut> lin=lr.findAll();
		if(lin.size()>0) {
			for(LogInOut l:lin) {
				
				Optional<User> u1=ur.findById(l.getId());
				 
				for(FIR f:u1.get().getFirFilled()) {
					if(f.getFirId()==FirId) {
						
						if(f.getFirTime().compareTo(LocalDateTime.now())<24) {
							fr.deleteById(FirId);
							u1.get().getFirFilled().remove(f);
							ur.save(u1.get());
							return "Fir deleted Successfully";
						}else {
							throw new FirException("It has been aleady more then 24 hours cant take back the FIR");
						}	
					}
				}
					throw new FirException("No fir present with this id");
			}
			throw new UserException("error");	
		}else {
			throw new UserException("You need to login First");
		}
		
	}




}
