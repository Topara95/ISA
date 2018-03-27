package project.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	List<User> findByName(String name);
	
	List<User> findBySurname(String surname);
	
	List<User> findByNameAndSurname(String name, String surname);
	
	List<User> findByNameStartingWith(String name);
	
	List<User> findBySurnameStartingWith(String surname);
	
	List<User> findByNameAndSurnameStartingWith(String name, String surname);
}

