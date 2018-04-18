package project.service;

import java.util.List;

import project.domain.Hall;
import project.domain.Seat;

public interface SeatService {
	
	Seat save(Seat seat);
	
	List<Seat> findByHall(Long hallId);
	
	Seat findByHallAndRowAndSeatInRow(Long hallId,int row, int seatInRow);
}
