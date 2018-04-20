package project.service;

import java.util.List;

import project.domain.Offer;

public interface OfferService {
	
	Offer save(Offer offer);
	
	List<Offer> findAll();
	
	Offer findById(Long id);
	
	List<Offer> findByIdNot(Long id);
	
	Offer deleteOffer(Long id);
	
	List<Offer> findByPropId(Long propId);
	
	Offer findByCreatedByAndPropId(Long createdBy,Long propId);
	
	Offer acceptOffer(Long id);

}
