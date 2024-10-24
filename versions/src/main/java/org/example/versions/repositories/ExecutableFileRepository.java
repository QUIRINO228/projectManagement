package org.example.versions.repositories;

import org.example.versions.models.ExecutableFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutableFileRepository extends MongoRepository<ExecutableFile, String> {
}
