package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.ThematicProps;

@Repository
public interface ThematicPropsRepository extends JpaRepository<ThematicProps,Long>{
	
	ThematicProps findById(Long id);
}
