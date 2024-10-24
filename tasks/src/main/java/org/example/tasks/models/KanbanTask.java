package org.example.tasks.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.tasks.models.enums.KanbanColumn;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "kanbanTasks")
public class KanbanTask extends Task {
    private KanbanColumn columnId;
}
