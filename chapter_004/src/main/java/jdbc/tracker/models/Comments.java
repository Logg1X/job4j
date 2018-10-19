package jdbc.tracker.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Comments {
    private int id;
    private String message;
    private long dateCreating;

    public Comments(String message) {
        this.message = message;
        dateCreating = System.currentTimeMillis();
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(long dateCreating) {
        this.dateCreating = dateCreating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comments comments = (Comments) o;
        return id == comments.id
                && dateCreating == comments.dateCreating
                && Objects.equals(message, comments.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, dateCreating);
    }

    @Override
    public String toString() {
        return "_________________________________________________" + System.lineSeparator()
                + "ID - " + this.id
                + " Дата создания: "
                + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(new Date(this.dateCreating)) + "." + System.lineSeparator()
                + " -©- " + message + System.lineSeparator()
                + "______________________________________________";

    }
}
