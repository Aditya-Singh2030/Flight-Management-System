package com.case_study.User.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.case_study.User.dao.UserDao;
import com.case_study.User.entity.Users;

@Service
public class MyUserService implements UserDetailsService{
	
	@Autowired
	private UserDao repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = repo.findByUserName(username);
		
		if(user == null) {
			System.out.println("404");
			throw new UsernameNotFoundException("404");
		}
		
		return new UserDetailImpl(user);
	}

}
