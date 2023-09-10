package ru.practikum.teamonesolution.Storage;

import ru.practikum.teamonesolution.models.Task;

import java.util.HashMap;
import java.util.Map;

public class TasksStorage {
    private int uniqueId;
    private final Map<Integer, Task> tasks = new HashMap<>();

    public Task add(Task task) {
        task.setId(generateId());
        tasks.put(uniqueId, task);
        return task;
    }

    public Task getById(int id) {
        return tasks.get(id);
    }

    private int generateId() {
        return ++uniqueId;
    }
}
