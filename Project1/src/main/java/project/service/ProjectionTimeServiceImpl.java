package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.EventProjection;
import project.domain.ProjectionTime;
import project.repository.EventProjectionRepository;
import project.repository.ProjectionTimeRepository;

@Service
@Transactional
public class ProjectionTimeServiceImpl implements ProjectionTimeService{
	
	@Autowired
	private ProjectionTimeRepository ptrepository;
	
	@Autowired
	private EventProjectionRepository eprepository;
	
	@Override
	public List<ProjectionTime> findByEventProjection(Long eventProjectionId) {
		EventProjection ep = eprepository.findOne(eventProjectionId);
		return ptrepository.findByEventProjection(ep);
	}

	@Override
	public ProjectionTime save(ProjectionTime projectionTime) {
		return ptrepository.save(projectionTime);
	}

}
