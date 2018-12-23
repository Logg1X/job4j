package crud.servlet.models;

import java.util.Objects;

public class Rule {
    private int id;
    private String name;

    public Rule(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Rule setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Rule setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule role = (Rule) o;
        return id == role.id &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Rule{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
