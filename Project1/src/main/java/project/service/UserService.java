package project.service;

import java.util.List;


import project.domain.User;

public interface UserService {
	
	User save(User user);
	
	void sendVerificationMail(User user);
	
	boolean verifyEmail(String email);
	
	List<User> findAll();
	
	User findByEmail(String email);
}
