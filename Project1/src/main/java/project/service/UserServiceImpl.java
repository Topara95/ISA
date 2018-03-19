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
		System.out.println("USAO U VERIFIKOVANJE");
		User user = userRepository.findByEmail(email);
		System.out.println("PROSAO UPIT!!! "+email);
		user.setVerified(true);
		userRepository.save(user);
		return true;
	}


	

	
}
