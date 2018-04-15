package project.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.UserConstants.*;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import project.TestUtil;
import project.constants.UserConstants;
import project.domain.User;
import project.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	
	private static final String URL_PREFIX = "/api/users";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetUsers() throws Exception {
		mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_USER_COUNT)))
				.andExpect(jsonPath("$.[*].id").value(hasItem(UserConstants.DB_ID.intValue())))
				.andExpect(jsonPath("$.[*].email").value(hasItem(DB_USER_EMAIL)))
				.andExpect(jsonPath("$.[*].name").value(hasItem(DB_USER_NAME)))
				.andExpect(jsonPath("$.[*].surname").value(hasItem(DB_USER_SURNAME)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testVerifyUser() throws Exception {
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		userService.save(user);
		
		mockMvc.perform(get(URL_PREFIX+"/verify/"+user.getId())).andExpect(status().isAccepted());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRegister() throws Exception{
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);

		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_PREFIX+"/register").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testModifyUser() throws Exception{
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(put(URL_PREFIX+"/"+DB_ID).contentType(contentType).content(json)).andExpect(status().isOk())
					.andExpect(jsonPath("$.name").value(user.getName()))
					.andExpect(jsonPath("$.surname").value(user.getSurname()));
	}
	
	@Test
	public void testSignIn() throws Exception{
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_PREFIX+"/login").contentType(contentType).content(json)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testSearchUsers() throws Exception{
		this.mockMvc.perform(get(URL_PREFIX+"/search/"+NEW_USER_NAME+"/"+NEW_USER_SURNAME)).andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(0)));
		this.mockMvc.perform(get(URL_PREFIX+"/search/nema/nema")).andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(DB_USER_COUNT)));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSendFriendRequest() throws Exception{
		User user = new User(NEW_USER_EMAIL,NEW_USER_PASSWORD,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_CITY,
				NEW_USER_PHONE,NEW_USER_TYPE);
		user.setVerified(true);
		userService.save(user);
		User user2 = new User(NEW_USER2_EMAIL,NEW_USER2_PASSWORD,NEW_USER2_NAME,NEW_USER2_SURNAME,NEW_USER2_CITY,
				NEW_USER2_PHONE,NEW_USER2_TYPE);
		user2.setVerified(true);
		userService.save(user2);
		
		String json = TestUtil.json(user);
		this.mockMvc.perform(post(URL_PREFIX+"/login").contentType(contentType).content(json)).andExpect(status().isOk());
		
		this.mockMvc.perform(get(URL_PREFIX+"/sendFriendRequest/"+user2.getId())).andExpect(status().isAccepted());
	}
}
