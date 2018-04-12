package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Reservation;
import project.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository reservationrepository;
	
	@Override
	public Reservation save(Reservation reservation) {
		return reservationrepository.save(reservation);
	}

}
