package crud.servlet.servlets;

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
        var user = logic.findById(req.getParameterMap());
        if (user != null) {
            var html = "<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>Edit User</title>"
                    + "</head>"
                    + "<body>"
                    + "<form action='" + req.getContextPath() + "/edit' method='post'>"
                    + "<input name='id' type='hidden' value='" + user.getId() + "'/>"
                    + "Name : <input type='text' name='name' value='" + user.getName() + "'>"
                    + "Login : <input type='text' name='login' value='" + user.getLogin() + "'>"
                    + "Email : <input type='text' name='email' value='" + user.getMail() + "'>"
                    + "<input type='submit' name='edit'></input>"
                    +"</br>"
                    +"</form>"
                    + "</body>"
                    + "</html>";
            PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
            printWriter.append(html);
            printWriter.flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> param = req.getParameterMap();
        User user = logic.update(param);
        var html = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Edit User</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/edit' method='post'>"
                + "<input name='id' type='hidden' value='" + user.getId()+ "'/>"
                + "Name : <input type='text' name='name' value='" + user.getName() + "'>"
                + "Login : <input type='text' name='login' value='" + user.getLogin() + "'>"
                + "Email : <input type='text' name='email' value='" + user.getMail() + "'>"
                + "<input type='submit' name='edit'></input>"
                +"</br>"
                +"Update successfully!"
                +"</form>"
                + "</body>"
                + "</html>";
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(html);
        printWriter.flush();
    }
}