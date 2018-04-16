package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.ThematicProps;
import project.domain.ThematicPropsType;

@Repository
public interface ThematicPropsRepository extends JpaRepository<ThematicProps,Long>{
	
	ThematicProps findById(Long id);
	List<ThematicProps> findByCulturalVenueId(Long culturalVenueId);
	List<ThematicProps> findByCulturalVenueIdAndTptype(Long culturalVenueId,ThematicPropsType tptype);
	List<ThematicProps> findByCulturalVenueIdAndTptypeAndCreatedBy(Long culturalVenueId,ThematicPropsType tptype,Long createdBy);
	List<ThematicProps> findByCulturalVenueIdAndTptypeAndCreatedByNot(Long culturalVenueId,ThematicPropsType tptype,Long createdBy);
}
