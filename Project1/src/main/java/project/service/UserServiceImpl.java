package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import project.domain.User;
import project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;


	@Override
	public User save(User user) {
		return userRepository.save(user);
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	@Override
	@Async
	public void sendVerificationMail(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Link za verifikaciju naloga");
		mail.setText("Pozdrav " + user.getName() + ",\n\n http://localhost:8080/api/verify/"+user.getEmail()+"");
		javaMailSender.send(mail);
	}


	@Override
	public boolean verifyEmail(String email) {
		email+=".com";
		User user = userRepository.findByEmail(email);
		user.setVerified(true);
		userRepository.save(user);
		return true;
	}


	@Override
	public User modifyUser(User user, String email) {
		email+=".com";
		User old = userRepository.findByEmail(email);
		if(user.getEmail() != null){
			old.setEmail(old.getEmail());
		}
		if(user.getName() != null){
			old.setName(user.getName());
		}
		if(user.getPassword() != null){
			old.setPassword(user.getPassword());
		}
		if(user.getSurname()!=null){
			old.setSurname(user.getSurname());
		}
		if(user.getCity()!=null){
			old.setCity(user.getCity());
		}
		if(user.getPhone()!=null){
			old.setPhone(user.getPhone());
		}
		
		//userRepository.delete(old);
		return userRepository.save(old);
	}


	@Override
	public User signIn(User user) {
		User user2 = userRepository.findByEmail(user.getEmail());
		if(user2 != null) {
			if(user.getPassword().equals(user2.getPassword()) && user2.isVerified() == true) {
				return user2;
			}
		}
		return null;
	}


	@Override
	public List<User> searchUsers(String name, String surname) {
		if(!name.equals("nema") && !surname.equals("nema")) {
			return userRepository.findByNameAndSurname(name, surname);
		}else if(name.equals("nema") && !surname.equals("nema")) {
			return userRepository.findBySurname(surname);
		}else if(!name.equals("nema") && surname.equals("nema")) {
			return userRepository.findByName(name);
		}else {
			return userRepository.findAll();
		}
	}


	@Override
	public User sendFriendRequest(String senderEmail, String receiverEmail) {
		receiverEmail+=".com";
		User user = userRepository.findByEmail(receiverEmail);
		if(user!=null) {
			user.getPendingRequests().add(senderEmail);
			userRepository.save(user);
		}
		return user;
	}


	@Override
	public User approveFriendRequest(String pendingEmail, String userEmail) {
		pendingEmail+=".com";
		User user = userRepository.findByEmail(userEmail);
		if(user != null) {
			user.getPendingRequests().remove(pendingEmail);
			user.getFriends().add(pendingEmail);
			userRepository.save(user);
		}
		return user;
	}


	

	
}
