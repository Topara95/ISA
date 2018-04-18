package project.service;

import java.util.List;

import project.domain.MembershipThreshold;

public interface MembershipThresholdService {
	
	MembershipThreshold modify(MembershipThreshold mt);
	
	MembershipThreshold save(MembershipThreshold mt);
	
	List<MembershipThreshold> findAll();

}
