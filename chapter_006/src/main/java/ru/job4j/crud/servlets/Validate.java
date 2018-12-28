package ru.job4j.crud.servlets;

import ru.job4j.crud.servlets.models.User;

import java.util.List;
import java.util.Map;

public interface Validate {
    int add(Map<String, String[]> param);

    User update(Map<String, String[]> user);

    User delete(Map<String, String[]> param);

    List<User> findAll();

    User findById(Map<String, String[]> param);

    User getByCredentional(String login, String password);

    boolean isAllowedaccess(User usr, String path);

    void close() throws Exception;
}
