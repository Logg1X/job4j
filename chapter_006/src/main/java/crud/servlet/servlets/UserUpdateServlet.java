package crud.servlet.servlets;

import crud.servlet.StoresException;
import crud.servlet.User;
import crud.servlet.Validate;
import crud.servlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String result = "";
        User user = null;
        try {
            user = logic.findById(req.getParameterMap());
        } catch (StoresException e) {
            result = e.getMessage();
        }
        req.setAttribute("result", result);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/UpdateUserPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> param = req.getParameterMap();
        String result;
        User user = null;
        try {
            user = logic.update(param);
            result = "Update successfuly";
        } catch (StoresException | NullPointerException e) {
            result = e.getMessage();
        }
        req.setAttribute("user", user);
        req.setAttribute("result",result);
        req.getRequestDispatcher("/WEB-INF/UpdateUserPage.jsp").forward(req, resp);
    }
}
