package org.example.projects.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Builder
@Document(collection = "users")
public class Project {
    @Id
    private String projectId;
    private String name;
    private String description;
    private List<String> teamIds;
    private List<String> taskIds;
    private List<String> versionIds;
}
