package project.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.constants.UserConstants.DB_USER_COUNT;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import project.constants.UserConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	private static final String URL_PREFIX = "/api/users";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetUsers() throws Exception {
		mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_USER_COUNT)));
				/*.andExpect(jsonPath("$.[*].id").value(hasItem(UserConstants.DB_ID.intValue())))
				.andExpect(jsonPath("$.[*].firstName").value(hasItem(DB_FIRST_NAME)))
				.andExpect(jsonPath("$.[*].lastName").value(hasItem(DB_LAST_NAME)))
				.andExpect(jsonPath("$.[*].index").value(hasItem(DB_INDEX)));*/
	}
}
