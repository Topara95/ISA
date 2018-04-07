package project.service;

import java.util.List;

import project.domain.EventProjection;
import project.domain.ProjectionTime;

public interface ProjectionTimeService {
	
	List<ProjectionTime> findByEventProjection(Long eventProjectionId);
	
	ProjectionTime save(ProjectionTime projectionTime);
}
