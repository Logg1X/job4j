package crud.servlet;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class User {
    private int id;
    private String name;
    private String login;
    private String mail;
    private LocalDateTime createDate;


    public User(int id, String name, String login, String mail) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.mail = mail;
        this.createDate = LocalDateTime.now();
    }

    public User(String name, String login, String mail) {
        this.name = name;
        this.login = login;
        this.mail = mail;
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getMail());
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(mail, user.mail)
                && Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, mail, createDate);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", mail='" + mail + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
