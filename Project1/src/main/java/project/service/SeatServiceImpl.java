package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Hall;
import project.domain.Seat;
import project.repository.HallRepository;
import project.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatRepository seatrepository;
	
	@Autowired
	private HallRepository hallrepository;
	
	@Override
	public Seat save(Seat seat) {
		return seatrepository.save(seat);
	}

	@Override
	public List<Seat> findByHall(Long hallId) {
		Hall hall = hallrepository.findOne(hallId);
		return seatrepository.findByHall(hall);
	}

}
