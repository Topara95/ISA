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
		mail.setText("Pozdrav " + user.getName() + ",\n\n http://localhost:8080/api/users/verify/"+user.getId()+"");
		javaMailSender.send(mail);
	}


	@Override
	public boolean verifyEmail(Long id) {
		User user = userRepository.findById(id);
		user.setVerified(true);
		userRepository.save(user);
		return true;
	}


	@Override
	public User modifyUser(User user, Long id) {
		User old = userRepository.findById(id);
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
	public User sendFriendRequest(Long sender, Long receiver) {
		User user = userRepository.findById(receiver);
		if(user!=null) {
			for(Long id : user.getPendingRequests()) {
				if(id == sender) {
					return null;
				}
			}
			for(Long id:user.getFriends()) {
				if(id == sender) {
					return null;
				}
			}
			user.getPendingRequests().add(sender);
			userRepository.save(user);
			return user;
		}
		return null;
	}


	@Override
	public User approveFriendRequest(Long pending, Long userId) {
		User receiverUser = userRepository.findById(userId);
		User senderUser = userRepository.findById(pending);
		boolean flag = false;
		for(Long id:receiverUser.getPendingRequests()) {
			if(id == pending) {
				flag =true;
			}
		}
		if(receiverUser != null && flag == true) {
			receiverUser.getPendingRequests().remove(pending);
			senderUser.getPendingRequests().remove(userId);
			receiverUser.getFriends().add(pending);
			senderUser.getFriends().add(userId);
			userRepository.save(receiverUser);
			return receiverUser;
		}
		return null;
	}


	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}


	

	
}
