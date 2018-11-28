package parser.vacancy;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Vacancy {

    private int id;
    private final String title;
    private final String link;
    private final String author;
    private final LocalDateTime date;

    public Vacancy(int id, String title, String link, String author, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.author = author;
        this.date = date;
    }

    public Vacancy(String title, String link, String author, LocalDateTime date) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(title, vacancy.title)
                && Objects.equals(link, vacancy.link)
                && Objects.equals(author, vacancy.author)
                && Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, author, date);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", author='" + author + '\''
                + ", date=" + date
                + '}';
    }
}
