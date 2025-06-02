package com.case_study.User.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.case_study.User.entity.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Long>{

	Users findByUserName(String userName);
	
}
