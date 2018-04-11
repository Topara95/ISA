package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.domain.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite,Long>{

}
