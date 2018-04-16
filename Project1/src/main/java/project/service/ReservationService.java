package project.service;

import java.util.List;

import project.domain.Reservation;

public interface ReservationService {
	
	Reservation save(Reservation reservation);
	
	List<Reservation> getReservationsForUser(Long userId);
	
	Reservation getReservation(Long resId);
	
	Reservation cancelReservation(Long resId);
}
