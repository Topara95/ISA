package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.User;
import project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;


	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	

	
}
