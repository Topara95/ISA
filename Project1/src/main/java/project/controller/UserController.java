package project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
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
import project.dto.UserDTO;
import project.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws Exception{
		userService.save(user);
		User savedUser = userService.findByEmail(user.getEmail());
		userService.sendVerificationMail(savedUser);
		UserDTO userdto = new UserDTO(user);
		return new ResponseEntity<UserDTO>(userdto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/verify/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> verifyUser(@PathVariable Long id) throws Exception{
		userService.verifyEmail(id);
		return new ResponseEntity<String>("verifikovan",HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> usersdto = new ArrayList<UserDTO>();
		for(User user : users) {
			usersdto.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(usersdto,HttpStatus.FOUND);
	}
	
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> modifyUser(@RequestBody User user, @PathVariable Long id){
		User modified = userService.modifyUser(user, id);
		return new ResponseEntity<UserDTO>(new UserDTO(modified),HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<String> signIn(@RequestBody User user,HttpSession session,HttpServletRequest request){
		User loggedUser = userService.signIn(user);
		if(loggedUser != null) {
			session.invalidate();
		    HttpSession newSession = request.getSession();
		    newSession.setAttribute("loggedUser", loggedUser);
		    return new ResponseEntity<String>("User logged in",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public ResponseEntity<String> signOut(HttpSession session, HttpServletRequest request){
		request.getSession().invalidate();
		return new ResponseEntity<String>("User logged out",HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/search/{name}/{surname}")
	public ResponseEntity<List<UserDTO>> searchUsers(@PathVariable String name, @PathVariable String surname){
		List<User> searched = userService.searchUsers(name, surname);
		List<UserDTO> searcheddto = new ArrayList<UserDTO>();
		for(User user : searched) {
			searcheddto.add(new UserDTO(user));
		}
		if(searched!=null) {
			return new ResponseEntity<List<UserDTO>>(searcheddto,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<List<UserDTO>>(searcheddto,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/sendFriendRequest/{receiverId}",method = RequestMethod.GET)
	public ResponseEntity<String> sendFriendRequest(@PathVariable Long receiverId,HttpServletRequest request){
		User sender = (User) request.getSession().getAttribute("loggedUser");		
		userService.sendFriendRequest(sender.getId(), receiverId);
		return new ResponseEntity<String>("zahtev uspesno poslat",HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/approveFriendRequest/{pendingId}",method = RequestMethod.GET)
	public ResponseEntity<String> approveFriendRequest(@PathVariable Long pendingId, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("loggedUser");
		userService.approveFriendRequest(pendingId, user.getId());
		return new ResponseEntity<String>("request approved",HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(value="/getFriends/{id}" , method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getFriends(@PathVariable Long id){
		List<User> friends = userService.getFriends(id);
		List<UserDTO> friendsDTO = new ArrayList<UserDTO>();
		for(User friend : friends) {
			friendsDTO.add(new UserDTO(friend));
		}
		return new ResponseEntity<List<UserDTO>>(friendsDTO,HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/getRequests/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getRequests(@PathVariable Long id){
		List<User> requests = userService.getFriendRequests(id);
		List<UserDTO> requestsDTO = new ArrayList<UserDTO>();
		for(User request : requests) {
			requestsDTO.add(new UserDTO(request));
		}
		return new ResponseEntity<List<UserDTO>>(requestsDTO,HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/test/",
			method = RequestMethod.GET
			)
	public void Test() {
		System.out.println("testiraj");
	}
	

}
