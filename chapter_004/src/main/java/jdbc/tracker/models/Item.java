package jdbc.tracker.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private long dateCreating;
    private long dateUpdate;
    private long crate;
    private String[] comments;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.dateCreating = System.currentTimeMillis();
        this.dateUpdate = this.dateCreating;
    }

    public Item(String name, String description, long crate) {
        this.name = name;
        this.description = description;
        this.crate = crate;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public void setDateCreating(long dateCreating) {
        this.dateCreating = dateCreating;
    }

    public long getDateUpdate() {
        return dateUpdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public long getDateCreating() {
        return dateCreating;
    }

    public Item setDateUpdate(long dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }

    @Override
    public String toString() {
        return "________________________________________________ " + System.lineSeparator()
                + "Задача №: " + this.id + "." + System.lineSeparator()
                + "Имя: " + this.name + "." + System.lineSeparator()
                + "Описание: " + this.description + "." + System.lineSeparator()
                + "Дата создания: " + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(new Date(dateCreating)) + "." + System.lineSeparator()
                + "Дата обновления: " + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(new Date(dateUpdate)) + "." + System.lineSeparator()
                + "________________________________________________";
    }
}
