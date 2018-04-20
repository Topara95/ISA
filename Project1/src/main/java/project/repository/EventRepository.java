package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Event;
import project.domain.ThematicProps;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{
	
	Event findById(Long id);
}
