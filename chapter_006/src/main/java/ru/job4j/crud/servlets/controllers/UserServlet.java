package ru.job4j.crud.servlets.controllers;

import ru.job4j.crud.servlets.StoresException;
import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();
    private final DispatchActions actions = new DispatchActions();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(logic.findAll().toString());
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Map<String, String[]> parameters = req.getParameterMap();
        String result;
        try {
            result = actions.execute(action, parameters);
        } catch (NullPointerException e) {
            result = "error: incorrect request";
        } catch (StoresException e) {
            result = e.getMessage();
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(this.getHtml(
                result,
                "<form action='" + req.getContextPath() + "/listUsr' method='get'>"
                        + "<input type='submit' value='OK'>"
                        + "</form>"));
        writer.flush();
    }

    private String getHtml(String message, String form) {
        return "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Edit User</title>"
                + "</head>"
                + "<body>"
                + message
                + form
                + "</body>"
                + "</html>";
    }

    private class DispatchActions {
        private HashMap<String, Function<Map<String, String[]>, String>> dispatch = new HashMap<>();

        private DispatchActions() {
            dispatch.put(
                    "add", stringMap -> {
                        logic.add(stringMap);
                        return "User added!";
                    }
            );
            dispatch.put(
                    "update",
                    stringMap -> {
                        logic.update(stringMap);
                        return "User updated!";
                    }
            );
            dispatch.put(
                    "delete",
                    stringMap -> {
                        logic.delete(stringMap);
                        return String.format("User with ID %s deleted!", stringMap.get("id")[0]);
                    }
            );
        }

        private String execute(String name, Map<String, String[]> param) {
            return dispatch.get(name).apply(param);
        }
    }
}
