package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.MembershipThreshold;

@Repository
public interface MembershipThresholdRepository extends JpaRepository<MembershipThreshold,Long>{
	MembershipThreshold findByValid(boolean valid);
}
