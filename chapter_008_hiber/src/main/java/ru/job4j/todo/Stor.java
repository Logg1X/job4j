package ru.job4j.todo;

import ru.job4j.todo.models.Task;

import java.util.List;

public interface Stor {
    void addTask(Task task);

    void updateStatus(String id);

    List<Task> getAllTasks();

    List getNoCompletedTasks();
}
