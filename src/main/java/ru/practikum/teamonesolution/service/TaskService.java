package ru.practikum.teamonesolution.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practikum.teamonesolution.Storage.TasksStorage;
import ru.practikum.teamonesolution.models.Task;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TasksStorage tasksStorage;

    public Task add(Task task) {
        return tasksStorage.add(task);
    }
}
