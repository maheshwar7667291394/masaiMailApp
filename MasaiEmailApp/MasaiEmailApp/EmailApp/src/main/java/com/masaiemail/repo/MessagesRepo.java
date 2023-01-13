package com.masaiemail.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masaiemail.model.Messages;

@Repository
public interface MessagesRepo  extends JpaRepository<Messages, Integer>{

}
