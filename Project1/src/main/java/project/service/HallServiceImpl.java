package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.CulturalVenue;
import project.domain.Hall;
import project.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService{
	
	@Autowired
	private HallRepository hallrepository;

	@Override
	public Hall save(Hall hall) {
		return hallrepository.save(hall);
	}
	


	

}
