package ru.job4j.crud.servlets.controllers;

import ru.job4j.crud.servlets.StoresException;
import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/CreateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> param = req.getParameterMap();
        String result;
        try {
            logic.add(param);
            result = "User added successfully!";
        } catch (NullPointerException e) {
            result = "error: incorrect request";
        } catch (StoresException e) {
            result = e.getMessage();
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/Status.jsp").forward(req, resp);
    }
}
