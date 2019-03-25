package ru.job4j.todo.models;

import java.sql.Timestamp;

public class Task {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
        this.created = new Timestamp(System.currentTimeMillis());
        this.done = false;
    }

    public Task() {
        this.created = new Timestamp(System.currentTimeMillis());
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + id
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", done=" + done
                + '}';
    }
}
