package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Offer;
import project.repository.OfferRepository;

@Service
@Transactional
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;

	@Override
	public Offer save(Offer offer) {
		return offerRepository.save(offer);
	}

	@Override
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	@Override
	public Offer findById(Long id) {
		return offerRepository.findById(id);
	}

	@Override
	public Offer deleteOffer(Long id) {
		Offer offer = offerRepository.findById(id);
		offerRepository.delete(offer);
		return offer;
	}

	@Override
	public List<Offer> findByPropId(Long propId) {
		return offerRepository.findByPropId(propId);
	}

	@Override
	public Offer findByCreatedByAndPropId(Long createdBy, Long propId) {
		return offerRepository.findByCreatedByAndPropId(createdBy, propId);
	}

}
