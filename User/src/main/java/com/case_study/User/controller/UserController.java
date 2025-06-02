package com.case_study.User.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.case_study.User.dto.Login;
import com.case_study.User.entity.Users;
import com.case_study.User.exception.UserNotFound;
import com.case_study.User.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
    private AuthenticationManager authenticationManager;
//	@GetMapping("")
//	public String test() {
//		return "working";
//	}
	
	@GetMapping("showAllUser")
	public List<Users> show(){
		return service.show();
	}
	
	@PostMapping("signUp")
	public Users signUp(@RequestBody Users user) {
		return service.signUp(user);
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody Login login) {
		
		boolean isValid = service.logIn(login.getUserName(), login.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
	}
	
	@PostMapping("token")
    public String getToken(@RequestBody Login login) throws UserNotFound {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(login.getUserName());
        } else {
            throw new UserNotFound("invalid access");
        }
    }
	
	@PutMapping("update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody Users user) throws UserNotFound{
        return ResponseEntity.ok(service.update(userId, user));
    }

}
