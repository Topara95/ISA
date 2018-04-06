package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Event;
import project.domain.EventProjection;

@Repository
public interface EventProjectionRepository extends JpaRepository<EventProjection, Long>{

	List<EventProjection> findByEvent(Event event);
	
}
