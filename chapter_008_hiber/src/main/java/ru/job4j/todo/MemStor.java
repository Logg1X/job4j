package ru.job4j.todo;

import ru.job4j.todo.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public enum MemStor implements Stor {
    INSTANSCE();

    private ConcurrentHashMap<String, Task> allTasks;

    MemStor() {
        this.allTasks = new ConcurrentHashMap<>();
        Task task = new Task("Починить шкаф в квартире");
        this.addTask(task);
    }

    public static void main(String[] args) {
        Task task = new Task("Тестовая задача");
        DBStore.INSTANCE.addTask(task);
    }

    @Override
    public void addTask(Task task) {
        task.setId(this.allTasks.size() + 1);
        this.allTasks.put(String.valueOf(task.getId()), task);
    }

    @Override
    public void updateStatus(String id) {
        Task result = this.allTasks.get(id);
        result.setDone(result.isDone() ? false : true);
        this.allTasks.replace(id, result);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.allTasks.values());
    }

    @Override
    public List<Task> getNoCompletedTasks() {
        return this.allTasks.values().stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }
}
