package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
