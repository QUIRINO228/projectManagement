package org.example.files.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "attachments")
public class Attachment {
    @Id
    private String attachmentId;
    private List<String> taskIds;
    private String fileName;
    private String filePath;
    private long fileSize;
}
