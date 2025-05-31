package com.case_study.User.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.case_study.User.dao.UserDao;
import com.case_study.User.entity.Users;
import com.case_study.User.exception.UserNotFound;


@Service
public class UserService {

	@Autowired
	private UserDao repo;
	@Autowired
	private JwtService jwt;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public List<Users> show() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Users signUp(Users user) {
		// TODO Auto-generated method stub
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public boolean logIn(String userName, String password) {
		Optional<Users> optionalUser = Optional.ofNullable(repo.findByUserName(userName));
        if (optionalUser.isPresent()) {
            return encoder.matches(password, optionalUser.get().getPassword());
        }
        return false;
	}

	public Object update(Long userId, Users user) throws UserNotFound {
		Users users = repo.findById(userId).orElseThrow(() -> new UserNotFound("User not found"));
        return repo.save(users);
	}

	public String generateToken(String userName) {
		return jwt.generateToken(userName);
	}

}
