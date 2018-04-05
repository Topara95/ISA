package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.EventProjection;

@Repository
public interface EventProjectionRepository extends JpaRepository<EventProjection, Long>{

	
	
}
