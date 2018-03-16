package project.aspect;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.domain.User;
import project.service.UserServiceImpl;

@Component
@Aspect
public class UserAspect {
	
	@Autowired
	private UserServiceImpl userService;

	@Around("execution(* project.service.UserService.save(..)) && args(user,..)")
	public void beforeRegister(ProceedingJoinPoint joinPoint,User user) throws Throwable {
		List<User> users = userService.findByEmail(user.getEmail());
		boolean isValid = true;
		if(users!=null){
			for(int i=0;i<users.size();i++){
				if(user.getEmail().equals(users.get(i).getEmail())){
					isValid = false;
				}
			}
		}
		
		if(isValid){
			joinPoint.proceed();
		}
		
	}
	
}
