package org.example.versions.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "versions")
@Builder
public class Version {
    @Id
    private String id;
    private String projectId;
    private String number;
    private String releaseDate;
    private String releaseNotes;
    private List<String> executableFileIds;
}

