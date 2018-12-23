package crud.servlet.servlets;

import crud.servlet.StoresException;
import crud.servlet.Validate;
import crud.servlet.ValidateService;
import crud.servlet.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        req.setCharacterEncoding("UTF-8");
        Map<String, String[]> param = req.getParameterMap();
        String result;
        User user = null;
        try {
            user = logic.update(param);
            result = "Update successfully!";
        } catch (StoresException | NullPointerException e) {
            result = e.getMessage();
        }
        req.setAttribute("user", user);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/Status.jsp").forward(req, resp);
    }
}
