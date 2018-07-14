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
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
                index = i;
                break;
            }
        }
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}