package org.example.tasks.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "agileTasks")
public class AgileTask extends Task {
    private String sprintId;
    private String storyPoints;
}