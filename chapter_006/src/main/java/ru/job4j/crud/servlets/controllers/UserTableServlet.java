package ru.job4j.crud.servlets.controllers;

import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.models.User;
import ru.job4j.crud.servlets.Validate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserTableServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = logic.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/listUsr.jsp").forward(req, resp);
        }
    }
