package project.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	
	User findByEmail(String email);
	
	List<User> findByName(String name);
	
	List<User> findBySurname(String surname);
	
	List<User> findByNameAndSurname(String name, String surname);
}

