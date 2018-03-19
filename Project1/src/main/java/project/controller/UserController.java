package project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.domain.User;
import project.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register/",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception{
		userService.sendVerificationMail(user);
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/verify/{email}", method = RequestMethod.GET)
	public ResponseEntity<String> verifyUser(@PathVariable String email) throws Exception{
		userService.verifyEmail(email);
		return new ResponseEntity<String>("verifikovan",HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.findAll(),HttpStatus.FOUND);
	}
	
	
	@RequestMapping(value="/{email}",method = RequestMethod.PATCH)
	public ResponseEntity<User> modifyUser(@RequestBody User user, @PathVariable String email){
		return new ResponseEntity<User>(userService.modifyUser(user, email),HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(value = "/test/",
			method = RequestMethod.GET
			)
	public void Test() {
		System.out.println("testiraj");
	}
	

}
