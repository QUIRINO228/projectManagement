package org.example.tasks.factory;

import org.example.tasks.models.RUPTask;
import org.example.tasks.models.Task;
import org.example.tasks.models.enums.RUPPhase;

public class RUPTaskFactory implements TaskFactory {
    @Override
    public Task createTask() {
        return RUPTask.builder()
                .phase(RUPPhase.valueOf("defaultPhase"))
                .build();
    }
}