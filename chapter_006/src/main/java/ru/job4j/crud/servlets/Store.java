package ru.job4j.crud.servlets;

import ru.job4j.crud.servlets.models.User;

import java.util.List;

public interface Store {

    int add(User user);

    User update(User user);

    User delete(int id);

    List<User> findAll();

    User findById(int id);

    User getByCredentional(String login, String password);
}
