package com.masai.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import com.masai.model.FIR;

public interface FirRepo extends JpaRepository<FIR, Integer>{

//	@Query("from Driver d where d.cabNo=?1")
//	public Driver findDriverByCab(String cabNo);
}
