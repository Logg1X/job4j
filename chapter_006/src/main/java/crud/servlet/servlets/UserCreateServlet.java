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
       String html = "<!DOCTYPE html>\n" +
               "<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Title</title>\n" +
               "    <style type=\"text/css\">" +
               "table {\n" +
               "     border-collapse: collapse;\n" +  /* Отображать двойные линии как одинарные */
               "    }\n" +
               "    th {\n" +
               "     background: #ccc; \n" + /* Цвет фона */
               "     text-align: left; \n" + /* Выравнивание по левому краю */
               "    }\n" +
               "    td, th {\n" +
               "     border: 1px solid #800; \n" + /* Параметры границы */
               "     padding: 4px;\n" +  /* Поля в ячейках */
               "    }" +
               " </style> " +
               "</head>\n" +
               "<body>\n" +
               "<form action='"+req.getContextPath()+"/createUser' method='post'>" +
               "Login : <input type='text' name='login'/>" +
               "Name : <input type='text' name='name'/>" +
               "Email : <input type='text' name='email'/>" +
               "<input type='submit' value='create'>" +
               "</form>" +
               "</body>\n" +
               "</html>";
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(html);
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> param = req.getParameterMap();
        String result;
        try {
            result = "completed successfully!" + logic.add(param);
        } catch (NullPointerException e) {
            result = "error: incorrect request";
        } catch (StoresException e) {
            result = e.getMessage();
        }
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(result);
        printWriter.flush();
    }
}
