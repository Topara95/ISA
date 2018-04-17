package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Reservation;
import project.domain.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{
	
	List<Reservation> findByOwner(User owner);
	
	List<Reservation> findByOwnerAndVisited(User owner, boolean visited);
}
