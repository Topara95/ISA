package project.service;

import java.util.List;

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
    @Transactional
    @Rollback(true) //it can be omitted because it is true by default
	public void testSave() {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		/*user.setEmail(NEW_USER_EMAIL);
		user.setPassword(NEW_USER_PASSWORD);
		user.setName(NEW_USER_NAME);
		user.setSurname(NEW_USER_SURNAME);
		user.setUsertype(NEW_USER_TYPE);*/
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
