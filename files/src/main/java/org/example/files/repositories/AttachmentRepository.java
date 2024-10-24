package org.example.files.repositories;

import org.example.files.models.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String> {
}
