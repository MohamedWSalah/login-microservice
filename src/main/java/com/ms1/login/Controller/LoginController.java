package com.ms1.login.Controller;


import com.ms1.login.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;


@RestController
public class LoginController {

	@Autowired
	private com.ms1.login.Repository.loginRepository loginRepository;

	@PostMapping(value ="/login" )
	public ResponseEntity<String> login( @RequestBody User user) throws NoSuchAlgorithmException, InvalidKeySpecException {

		//fake salt used for testing
		byte[] salt = "fakesalt".getBytes();
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");


		User user1 = loginRepository.findByEmail(user.getEmail());
		if(user1 == null) {
			return ResponseEntity.badRequest().body("User not found");
		}

		PBEKeySpec pbeKeySpec2 = new PBEKeySpec(user.getPassword().toCharArray(), salt, 10, 512);
		byte[] hash2 = skf.generateSecret(pbeKeySpec2).getEncoded();
		String base64Hash2 = Base64.getMimeEncoder().encodeToString(hash2);


		if(!user1.getPassword().equals(base64Hash2)) {
			return ResponseEntity.badRequest().body("Password is incorrect");
		}
		return ResponseEntity.ok("Login Successful "+ user.toString());
	}



}
