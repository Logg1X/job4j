package crud.servlet.servlets;

import crud.servlet.StoresException;
import crud.servlet.Validate;
import crud.servlet.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        var html = this.getHtml(
                "",
                "<form action='" + req.getContextPath() + "/createUser' method='post'>"
                        + "Name : <input type='text' name='name'/>"
                        + "Login : <input type='text' name='login'/>"
                        + "Email : <input type='text' name='email'/>"
                        + "<input type='submit' value='create'>"
                        + "</form>",
                ""
        );
        printWriter.append(html);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> param = req.getParameterMap();
        String result;
        try {

            result = this.getHtml(
                    "User added successfully with ID: !",
                    String.format("%s", logic.add(param)),
                    "<form action='" + req.getContextPath() + "/usersTable' method='get'>"
                            + "<input type='submit' value='OK'>"
                            + "</form>"
            );
        } catch (NullPointerException e) {
            result = "error: incorrect request";
        } catch (StoresException e) {
            result = e.getMessage();
        }
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(result);
        printWriter.flush();
    }

    private String getHtml(String message, String method, String form) {
        return "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create User</title>"
                + "</head>"
                + "<body>"
                + message
                + method
                + form
                + "</body>"
                + "</html>";
    }
}
