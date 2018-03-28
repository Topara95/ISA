package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.CulturalVenue;
import project.domain.CulturalVenueType;

@Repository
public interface CulturalVenueRepository extends JpaRepository<CulturalVenue,Long> {
	
	List<CulturalVenue> findByCvtype(CulturalVenueType cvt);
	
}
