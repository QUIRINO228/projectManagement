package org.example.versions.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "versions")
@Builder
public class ExecutableFile {
    @Id
    private String id;
    private String versionId;
    private String name;
    private String path;
    private long size;
}
