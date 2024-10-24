package org.example.projects.repositories;

import org.example.projects.models.Requirement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends MongoRepository<Requirement, String> {
}
