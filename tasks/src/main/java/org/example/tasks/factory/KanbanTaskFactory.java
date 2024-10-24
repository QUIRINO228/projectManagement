package org.example.tasks.factory;

import org.example.tasks.models.KanbanTask;
import org.example.tasks.models.Task;
import org.example.tasks.models.enums.KanbanColumn;

public class KanbanTaskFactory implements TaskFactory {
    @Override
    public Task createTask() {
        return KanbanTask.builder()
                .columnId(KanbanColumn.valueOf("defaultColumnId"))
                .build();
    }
}