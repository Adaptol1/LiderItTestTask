package main.model;

import main.model.TeamMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMembersRepository extends CrudRepository<TeamMember, Integer> {
}
