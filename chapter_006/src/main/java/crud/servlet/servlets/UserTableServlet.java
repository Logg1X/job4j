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
//        resp.setContentType("text/html");
//        List<User> users = null;
//        try {
//            users = logic.findAll();
//        } catch (StoresException e) {
//            e.printStackTrace();
//        }
//        String html = "<!DOCTYPE html>"
//                + "<html lang=\"en\">"
//                + "  <head>"
//                + "    <meta charset=\"UTF-8\">"
//                + "      <title>Users List</title>"
//                + "        <style type=\"text/css\">"
//                + "           table {"
//                + "           border-collapse: collapse;" /* Отображать двойные линии как одинарные */
//                + "           }"
//                + "           th {"
//                + "           background: #ccc;" /* Цвет фона */
//                + "           text-align: left;" /* Выравнивание по левому краю */
//                + "           }"
//                + "           td, th {"
//                + "           border: 1px solid #800;" /* Параметры границы */
//                + "           padding: 4px;" /* Поля в ячейках */
//                + "           }"
//                + "       </style> "
//                + "  </head>"
//                + "    <body>"
//                + createPage(users, req)
//                + "    </body>"
//                + "</html>";
//        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
//        printWriter.append(html);
//        printWriter.flush();
//        resp.sendRedirect(String.format("%s/WEB-INF/UserTable.jsp", req.getContextPath()));
        req.getRequestDispatcher("WEB-INF/UserTable.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private String createPage(List<User> users, HttpServletRequest req) {
        StringBuilder builder = new StringBuilder("<Table>");
        String createForm = "<form action='" + req.getContextPath() + "/createUser' method='get'>"
                + "<input type='submit' value='ID      +'>"
                + "</form>";
        if (users != null && !users.isEmpty()) {
            builder.append("<tr>"
                    + "<th>" + createForm + "</th>"
                    + "<th>NAME</th>"
                    + "<th>LOGIN</th>"
                    + "<th>EMAIL</th>"
                    + "<th>EDIT</th>"
                    + "<th>DELETE</th>"
                    + "</tr>"
            );
            for (User user : users) {
                builder.append("<tr>"
                        + "<td>" + user.getId() + "</td>"
                        + "<td>" + user.getName() + "</td>"
                        + "<td>" + user.getLogin() + "</td>"
                        + "<td>" + user.getMail() + "</td>"
                        + "<td>"
                        + "<form action='" + req.getContextPath() + "/edit?id=<%id%>' method='get'>"
                        + "<input type='hidden' name='id' value=" + user.getId() + ">"
                        + "<input type='submit' value='Edit'>"
                        + "</form>"
                        + "</td>"
                        + "<td>"
                        + "<form action='" + req.getContextPath() + "/users?action=<%action%>id=<%id%>' method='post'>"
                        + "<input type='hidden' name='action' value='delete'>"
                        + "<input type='hidden' name='id' value=" + user.getId() + ">"
                        + "<input type='submit' value='Delete'>"
                        + "</form>"
                        + "</td>"
                        + "</tr>"
                );
            }
        } else {
            builder.append("<tr><td>Users list is empty. Click '+' to add " + createForm + "</td></tr>");
        }
        builder.append("</Table>");
        return builder.toString();
    }
}
