package jdbc.tracker.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Item {
    private String id;
    private String name;
    private String description;
    private String dateCreating;
    private String dateUpdate;
    private long crate;
    private String[] comments;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.dateCreating = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
                .format(new Date());
        this.dateUpdate = this.dateCreating;
    }

    public Item(String name, String description, long crate) {
        this.name = name;
        this.description = description;
        this.crate = crate;
    }

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public String getDateCreating() {
        return dateCreating;
    }

    public Item setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
        return this;
    }

    @Override
    public String toString() {
        return "________________________________________________ " + System.lineSeparator()
                + "Задача №: " + this.id + "." + System.lineSeparator()
                + "Имя: " + this.name + "." + System.lineSeparator()
                + "Описание: " + this.description + "." + System.lineSeparator()
                + "Дата создания: " + this.dateCreating + "." + System.lineSeparator()
                + "Дата обновления: " + this.dateUpdate + "." + System.lineSeparator()
                + "________________________________________________";
    }
}
