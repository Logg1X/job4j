package ru.job4j.crud.servlets.controllers;

import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = logic.getByCredentional(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            session.setAttribute("access", true);
            resp.sendRedirect(String.format("%s/Index.html", req.getContextPath()));
        } else {
            req.setAttribute("result", "Invalid credentional!");
            req.getRequestDispatcher("/WEB-INF/Status.jsp").forward(req, resp);
        }
    }
}
