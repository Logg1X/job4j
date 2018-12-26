package ru.job4j.crud.servlets.models;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Role role;
    private String mail;
    private LocalDateTime createDate;


    public User(String name, String login, String mail, String password, LocalDateTime createDate) {
        this.name = name;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.createDate = createDate;
    }

    public User(String name, String login, String password, String mail) {
        this(name, login, mail, password, now());
    }

    public User(int id, String name, String login, String mail, String password, Role role, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.mail = mail;
        this.createDate = createDate;
    }
    public User(String name, String login, String mail, String password, Role role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.mail = mail;
        this.createDate = now();
    }

    public User(int id, String name, String login, String mail, String password, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.mail = mail;
        this.password = password;
        this.role = Role.valueOf(role);
        this.createDate = now();
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

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
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
                + ", password='" + password + '\''
                + ", role=" + role
                + ", mail='" + mail + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
