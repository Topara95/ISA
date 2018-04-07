package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.EventProjection;
import project.domain.ProjectionTime;

@Repository
public interface ProjectionTimeRepository extends JpaRepository<ProjectionTime,Long>{
	
	List<ProjectionTime> findByEventProjection(EventProjection eventProjection);
	
}
