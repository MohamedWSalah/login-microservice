package com.ms1.login.Controller;


import com.ms1.login.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

	@Autowired
	private com.ms1.login.Repository.loginRepository loginRepository;

	@PostMapping(value ="/login" )
	public ResponseEntity<String> login( @RequestBody User user) {
		User user1 = loginRepository.findByEmail(user.getEmail());
		if(user1 == null) {
			return ResponseEntity.badRequest().body("User not found");
		}
		if(!user1.getPassword().equals(user.getPassword())) {
			return ResponseEntity.badRequest().body("Password is incorrect");
		}
		return ResponseEntity.ok("Login Successful "+ user.toString());
	}



}
