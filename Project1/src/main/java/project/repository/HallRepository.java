package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long>{

}
