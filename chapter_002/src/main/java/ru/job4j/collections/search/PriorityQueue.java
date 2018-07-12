package ru.job4j.collections.search;

import ru.job4j.collections.search.models.Task;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод добавляет задачу по приоретету.
     *
     * @param task задача
     */
    public void put(Task task) {
        if (this.tasks.isEmpty()) {
            tasks.add(task);
            return;
        }
        for (Task task1 : tasks) {
            if (task1.getPriority() > task.getPriority()) {
                this.tasks.add(tasks.indexOf(task1), task);
                break;
            }
        }

    }

    public Task take() {
        return this.tasks.poll();
    }
}