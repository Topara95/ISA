package project.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static project.constants.UserConstants.*;

import project.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void testFindAll(){
		List<User> users = userService.findAll();
		assertThat(users).hasSize(DB_USER_COUNT);
	}
	
	
	@Test
	public void testFindByEmail() {
		User user = userService.findByEmail(DB_USER_EMAIL);
		assertThat(user).isNotNull();
		
		assertThat(user.getEmail()).isEqualTo(DB_USER_EMAIL);
		assertThat(user.getName()).isEqualTo(DB_USER_NAME);
		assertThat(user.getSurname()).isEqualTo(DB_USER_SURNAME);
	}
	
	@Test
	public void testFindById() {
		User user = userService.findById(DB_ID);
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(DB_ID);
	}
	
	@Test
	public void testSignIn() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		User dbUser = userService.signIn(user);
		assertThat(dbUser).isNull();
	}
	
	@Test
	public void testSearchUsers() {
		List<User> users;
		users = userService.searchUsersStartingWith(SEARCH_NAME, SEARCH_SURNAME);
		assertThat(users.size()).isEqualTo(SEARCH_RESULT);
		
		users = userService.searchUsersStartingWith("nema", "nema");
		assertThat(users.size()).isEqualTo(DB_USER_COUNT);
	}
	
	@Test
	@Transactional
	public void testGetFriends() {
		List<User> friends;
		friends = userService.getFriends(DB_ID);
		assertThat(friends).isNotNull();
		assertThat(friends.size()).isEqualTo(DB_1_FRIENDS);
	}
	
	@Test
	@Transactional
	public void testGetFriendRequests() {
		userService.sendFriendRequest(DB_ID, DB_ID_FRIEND);
		List<User> requests = userService.getFriendRequests(DB_ID_FRIEND);
		assertThat(requests).isNotNull();
		assertThat(requests.size()).isEqualTo(DB_REQUESTS_SIZE);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSendFriendRequest() {
		User receiver = userService.sendFriendRequest(DB_ID, DB_ID_FRIEND);
		assertThat(receiver).isNotNull();
		Hibernate.initialize(receiver.getReceivedRequests());
		assertThat(receiver.getReceivedRequests().contains(userService.findById(DB_ID)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeclineFriendRequest() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		userService.save(user);
		User user2 = new User(NEW_USER2_EMAIL,NEW_USER2_PASSWORD,NEW_USER2_NAME,NEW_USER2_SURNAME,NEW_USER2_CITY,
				NEW_USER2_PHONE,NEW_USER2_TYPE);
		userService.save(user2);
		userService.sendFriendRequest(user.getId(), user2.getId());
		User declined = userService.declineFriendRequest(user.getId(), user2.getId());
		assertThat(declined).isNotNull();
		assertThat(!user2.getReceivedRequests().contains(user));
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveFriend() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		userService.save(user);
		User user2 = new User(NEW_USER2_EMAIL,NEW_USER2_PASSWORD,NEW_USER2_NAME,NEW_USER2_SURNAME,NEW_USER2_CITY,
				NEW_USER2_PHONE,NEW_USER2_TYPE);
		userService.save(user2);
		userService.sendFriendRequest(user.getId(), user2.getId());
		userService.approveFriendRequest(user.getId(), user2.getId());
		User removed = userService.removeFriend(user.getId(), user2.getId());
		assertThat(removed).isNotNull();
		assertThat(!user2.getFriends().contains(removed));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testApproveFriendRequest() {
		userService.sendFriendRequest(DB_ID, DB_ID_FRIEND);
		User receiver = userService.approveFriendRequest(DB_ID, DB_ID_FRIEND);
		assertThat(receiver).isNotNull();
		Hibernate.initialize(receiver.getFriendOf());
		Hibernate.initialize(receiver.getFriends());
		Hibernate.initialize(receiver.getReceivedRequests());
		assertThat(!receiver.getReceivedRequests().contains(userService.findById(DB_ID)));
		assertThat(receiver.getFriends().contains(userService.findById(DB_ID)));
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testVerifyEmail() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		userService.save(user);
		
		User dbUser = userService.findByEmail(NEW_USER_EMAIL);
		userService.verifyEmail(dbUser.getId());
		assertThat(dbUser.isVerified()).isEqualTo(true);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyUser() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		User modified = userService.modifyUser(user, DB_ID);
		assertThat(modified).isNotNull();
		assertThat(modified.getPassword()).isEqualTo(NEW_USER_PASSWORD);
		assertThat(modified.getName()).isEqualTo(NEW_USER_NAME);
		assertThat(modified.getSurname()).isEqualTo(NEW_USER_SURNAME);
		assertThat(modified.getCity()).isEqualTo(NEW_USER_CITY);
		assertThat(modified.getPhone()).isEqualTo(NEW_USER_PHONE);
	}
	
	@Test
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testSave() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		user.setVerified(true);
		System.out.println("############"+user);
		
		int dbSizeBeforeAdd = userService.findAll().size();
		
		User dbUser = userService.save(user);
		System.out.println("############"+dbUser);
		assertThat(dbUser).isNotNull();
				
		// Validate that new student is in the database
        List<User> users = userService.findAll();
        assertThat(users).hasSize(dbSizeBeforeAdd + 1);
        dbUser = users.get(users.size() - 1); //get last student
        assertThat(dbUser.getEmail()).isEqualTo(NEW_USER_EMAIL);
        assertThat(dbUser.getPassword()).isEqualTo(NEW_USER_PASSWORD);
        assertThat(dbUser.getName()).isEqualTo(NEW_USER_NAME);
        assertThat(dbUser.getSurname()).isEqualTo(NEW_USER_SURNAME);
        assertThat(dbUser.getUsertype()).isEqualTo(NEW_USER_TYPE);
	}

}
