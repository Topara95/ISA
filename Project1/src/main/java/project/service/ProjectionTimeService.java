package project.service;

import java.util.List;

import project.domain.EventProjection;
import project.domain.ProjectionTime;
import project.domain.Seat;

public interface ProjectionTimeService {
	
	List<ProjectionTime> findByEventProjection(Long eventProjectionId);
	
	ProjectionTime save(ProjectionTime projectionTime);
	
	ProjectionTime findOne(Long ptId);
	
	List<Seat> reserveSeats(Long projectiontimeId,List<String> seatinfo, Long userId);
	
	List<Seat> getTakenSeats(Long projectiontimeId);
}
