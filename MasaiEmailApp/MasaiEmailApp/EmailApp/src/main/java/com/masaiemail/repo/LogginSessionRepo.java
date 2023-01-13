package com.masaiemail.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaiemail.model.LogginSession;

@Repository
public interface LogginSessionRepo extends JpaRepository<LogginSession, Integer> {
	
	public Optional<LogginSession> findByKey(String key);
	
	public Optional<LogginSession> findByEmail(String email);
	
		
}
