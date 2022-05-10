package com.ms1.login.Repository;

import com.ms1.login.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface loginRepository  extends MongoRepository<User, String> {
	public User findByEmailAndPassword(String email, String password);
	public User findByEmail(String email);
}
