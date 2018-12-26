package ru.job4j.crud.servlets.models;

import java.util.Objects;

public class Rule {
    private int id;
    private String name;
    private String accessPath;

    public Rule(int id, String name, String accessPath) {
        this.id = id;
        this.name = name;
        this.accessPath = accessPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id
                && Objects.equals(name, rule.name)
                && Objects.equals(accessPath, rule.accessPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accessPath);
    }

    @Override
    public String toString() {
        return "Rule{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", accessPath='" + accessPath + '\''
                + '}';
    }
}