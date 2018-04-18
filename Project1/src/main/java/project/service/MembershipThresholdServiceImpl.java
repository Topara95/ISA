package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.MembershipThreshold;
import project.repository.MembershipThresholdRepository;

@Service
public class MembershipThresholdServiceImpl implements MembershipThresholdService {
	
	@Autowired
	private MembershipThresholdRepository mtRepository;

	@Override
	public MembershipThreshold modify(MembershipThreshold mt) {
		MembershipThreshold mt1 = mtRepository.findByValid(true);
		mt1.setBronzeThreshold(mt.getBronzeThreshold());
		mt1.setGoldThreshold(mt.getGoldThreshold());
		mt1.setSilverThreshold(mt.getSilverThreshold());
		return mtRepository.save(mt1);
	}

	@Override
	public MembershipThreshold save(MembershipThreshold mt) {
		mtRepository.save(mt);
		return mt;
	}

	@Override
	public List<MembershipThreshold> findAll() {
		return mtRepository.findAll();
	}

}
