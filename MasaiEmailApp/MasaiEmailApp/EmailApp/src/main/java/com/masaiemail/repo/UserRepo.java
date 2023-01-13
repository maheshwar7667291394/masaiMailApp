package com.masaiemail.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaiemail.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
