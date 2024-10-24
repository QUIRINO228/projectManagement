package org.example.tasks.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.tasks.models.enums.RUPPhase;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "rupTasks")
public class RUPTask extends Task {
    private RUPPhase phase;
}