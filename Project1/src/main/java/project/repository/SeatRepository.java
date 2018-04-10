package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Hall;
import project.domain.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long>{
	
	List<Seat> findByHall(Hall hall);
	
	Seat findByHallAndRowAndSeatInRow(Hall hall,int row, int seatInRow);
}
