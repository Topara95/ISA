package project.service;

import java.util.List;


import project.domain.User;

public interface UserService {
	
	User save(User user);
	
	void sendVerificationMail(User user);
	
	boolean verifyEmail(String email);
	
	User signIn(User user);
	
	User modifyUser(User user, String email);
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	List<User> searchUsers(String name,String surname);
	
	User sendFriendRequest(String senderEmail,String receiverEmail);
	
	User approveFriendRequest(String pendingEmail,String userEmail);
}
