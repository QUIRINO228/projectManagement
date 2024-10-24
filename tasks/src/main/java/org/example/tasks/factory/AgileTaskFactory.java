package org.example.tasks.factory;

import org.example.tasks.models.AgileTask;
import org.example.tasks.models.Task;

public class AgileTaskFactory implements TaskFactory {
    @Override
    public Task createTask() {
        return AgileTask.builder()
                .sprintId("defaultSprintId")
                .storyPoints("defaultStoryPoints")
                .build();
    }
}
