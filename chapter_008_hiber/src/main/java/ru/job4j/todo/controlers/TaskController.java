package ru.job4j.todo.controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.todo.DBStorage;
import ru.job4j.todo.Stor;
import ru.job4j.todo.models.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TaskController extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(TaskController.class.getName());
    private ObjectMapper mapper = new ObjectMapper();
    //    private Stor stor = MemoryStorage.INSTANSCE;
    private Stor stor = DBStorage.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> result;
        String checkComplete = req.getParameter("checkComplete");
        LOGGER.info("----Method GET----Request paramKeys: " + req.getParameterMap().keySet().toString());
        if ("noCompleteTask".equals(checkComplete)) {
            result = stor.getNoCompletedTasks();
        } else {
            result = stor.getAllTasks();
        }
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(mapper.writeValueAsString(result));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var param = req.getParameterMap();
        LOGGER.info("----Method POST----");
        LOGGER.info("Request paramKeys: " + req.getParameterMap().keySet().toString());
        if (param.keySet().contains("desc")) {
            String desc = req.getParameter("desc");
            var t = new Task(desc);
            stor.addTask(t);
            LOGGER.info("----Client added new task with id: " + t.getId() + ".---");
        } else {
            String id = req.getParameter("id");
            stor.updateStatus(id);
            LOGGER.info("----Client changed task status with id: " + id + ".---");
        }
    }

    @Override
    public void destroy() {
        try {
            DBStorage.INSTANCE.close();
            super.destroy();
            LOGGER.info("Connection is closed");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
