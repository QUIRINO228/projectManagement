package org.example.projects.models;

import lombok.Builder;
import lombok.Data;
import org.example.projects.models.enun.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "requirements")
public class Requirement  {
    @Id
    private String requirementId;
    private String projectId;
    private String description;
    private Status status;
}