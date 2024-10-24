package org.example.tasks.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String taskId;
    private String projectId;
    private String description;
    private String status;
    private List<String> assigneeIds;
    private String methodology;
    private String priority;
    private List<String> attachmentIds;
}