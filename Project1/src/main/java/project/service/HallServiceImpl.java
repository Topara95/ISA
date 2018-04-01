package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Hall;

@Service
@Transactional
public class HallServiceImpl implements HallService{
	
	@Autowired
	private HallService hallservice;

	@Override
	public Hall save(Hall hall) {
		return hallservice.save(hall);
	}

}
