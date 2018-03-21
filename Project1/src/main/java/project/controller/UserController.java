package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception{
		userService.save(user);
		User savedUser = userService.findByEmail(user.getEmail());
		userService.sendVerificationMail(savedUser);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/verify/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> verifyUser(@PathVariable Long id) throws Exception{
		userService.verifyEmail(id);
		return new ResponseEntity<String>("verifikovan",HttpStatus.ACCEPTED);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.findAll(),HttpStatus.FOUND);
	}
	
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<User> modifyUser(@RequestBody User user, @PathVariable Long id){
		return new ResponseEntity<User>(userService.modifyUser(user, id),HttpStatus.ACCEPTED);
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
	public ResponseEntity<List<User>> searchUsers(@PathVariable String name, @PathVariable String surname){
		List<User> searched = userService.searchUsers(name, surname);
		if(searched!=null) {
			return new ResponseEntity<List<User>>(searched,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<List<User>>(searched,HttpStatus.NOT_FOUND);
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
	
	@RequestMapping(value = "/test/",
			method = RequestMethod.GET
			)
	public void Test() {
		System.out.println("testiraj");
	}
	

}
