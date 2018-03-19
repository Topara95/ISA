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
		User users = userService.findByEmail(user.getEmail());
		boolean isValid = true;
		if(users!=null){
			
				if(user.getEmail().equals(users.getEmail())){
					System.out.println("IMA VEC!!!!");
					isValid = false;
				}
			
		}
		
		if(isValid){
			joinPoint.proceed();
		}
		
	}
	
}
