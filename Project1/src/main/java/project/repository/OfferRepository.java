package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>{
	
	Offer findByCreatedByAndPropId(Long createdBy,Long propId);
	Offer findById(Long id);
	List<Offer> findByPropId(Long propId);
}
