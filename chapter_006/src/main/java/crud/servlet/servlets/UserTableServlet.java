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
import java.util.List;

public class UserTableServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<User> users = null;
        try {
            users = logic.findAll();
        } catch (StoresException e) {
            e.printStackTrace();
        }
        users = logic.findAll();
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Users List</title>\n" +
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
                createForm(users, req)+
                "</body>\n" +
                "</html>";
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(html);
        printWriter.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private String createForm(List<User> users, HttpServletRequest req) {
        StringBuilder builder = new StringBuilder("<Table>");
        if (users != null || !users.isEmpty()) {
            builder.append(
                    "<tr>" +
                            "<th>ID</th>" +
                            "<th>NAME</th>" +
                            "<th>LOGIN</th>" +
                            "<th>EMAIL</th>" +
                            "<th>EDIT</th>" +
                            "<th>DELETE</th>" +
                            "</tr>");
            for (User user : users) {
                builder.append("<tr>" +
                        "<td>" + user.getId() + "</td>" +
                        "<td>" + user.getName() + "</td>" +
                        "<td>" + user.getLogin() + "</td>" +
                        "<td>" + user.getMail() + "</td>" +
                        "<td>" +
                        "<form action='" + req.getContextPath() + "/usersTable' method='get'>" +
                        "<input type='submit' value=''>" +
                        "</form>"+
                        "</td>"+
                        "<td>" +
                        "<form action='" + req.getContextPath() + "/deleteUser' method='post'>" +
                        "<input type='submit' value='Delete'>" +
                        "</form>"+
                        "</td>"+
                        "</tr>"
                );
            }
        } else {
            builder.append("<tr><td> List is empty </td></tr>");
        }
        builder.append("</Table>");
        return builder.toString();
    }
}
