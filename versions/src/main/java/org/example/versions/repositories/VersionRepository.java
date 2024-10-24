package org.example.versions.repositories;

import org.example.versions.models.Version;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends MongoRepository<Version, String> {
}
