package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.domain.Offer;
import project.domain.ThematicProps;
import project.repository.OfferRepository;
import project.repository.ThematicPropsRepository;

@Service
@Transactional
public class OfferServiceImpl implements OfferService{
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ThematicPropsRepository thematicPropsRepository;

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

	@Override
	public Offer acceptOffer(Long id) {
		Offer offer = offerRepository.findById(id);
		ThematicProps prop = thematicPropsRepository.findById(offer.getPropId());
		prop.setReserved("YES");
		offer.setApproved(true);
		System.out.println("nasao pravog!");
		List<Offer> others =  offerRepository.findByIdNot(id);
		for(int i=0;i<others.size();i++) {
			
			if(others.get(i).getPropId()==offer.getPropId()) {
				System.out.println("brisem "+others.get(i).getId());
				offerRepository.delete(others.get(i));
			}
		}
		thematicPropsRepository.save(prop);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public List<Offer> findByIdNot(Long id) {
		return offerRepository.findByIdNot(id);
	}

}
