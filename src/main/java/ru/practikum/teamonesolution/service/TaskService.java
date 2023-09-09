package ru.practikum.teamonesolution.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practikum.teamonesolution.Storage.TasksStorage;
import ru.practikum.teamonesolution.models.Task;

@Service
@RequiredArgsConstructor
public class TaskService {
    private TasksStorage tasksStorage = new TasksStorage();

    public Task add(Task task) {
        tasksStorage.add(task);
        return task;
    }
}
