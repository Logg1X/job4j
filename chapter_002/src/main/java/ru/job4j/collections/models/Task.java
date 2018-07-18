package ru.job4j.collections.models;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Task {
    /**
     * Описание задачи.
     */
    private String desc;
    /**
     * Приоритет задачи.
     */
    private int priority;

    /**
     * Конструктор класса.
     *
     * @param description     desc
     * @param prt priority
     */
    public Task(final String description, final int prt) {
        this.desc = description;
        this.priority = prt;
    }

    /**
     * @return desc
     */
    public final String getDesc() {
        return desc;
    }

    /**
     * @return priority
     */
    public final int getPriority() {
        return priority;
    }
}
