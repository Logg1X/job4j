package crud.servlet.servlets;

import crud.servlet.Validate;
import crud.servlet.ValidateService;
import crud.servlet.models.User;

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

        req.getRequestDispatcher("WEB-INF/UserTable.jsp").forward(req, resp);
    }
}
