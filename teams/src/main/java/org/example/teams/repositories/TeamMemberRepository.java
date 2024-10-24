package org.example.teams.repositories;

import org.example.teams.models.TeamMember;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends MongoRepository<TeamMember, String> {
}
