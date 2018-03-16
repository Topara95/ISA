package project.service;

import java.util.List;


import project.domain.User;

public interface UserService {
	
	User save(User user);
	
	List<User> findAll();
	
	List<User> findByEmail(String email);
}
