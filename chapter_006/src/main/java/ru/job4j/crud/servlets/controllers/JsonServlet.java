package ru.job4j.crud.servlets.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.crud.servlets.models.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

public class JsonServlet extends HttpServlet {
    private final ConcurrentHashMap<String, Person> map = new ConcurrentHashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        map.put("1", new Person("Pasha", "Toporov", "per4mancerror@gmail.com", "qwerty", true));
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, map.values());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String jsonText = toSBuilder(br).toString();
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(jsonText, Person.class);
        int id = map.size() + 1;
        map.put(String.valueOf(id), person);
    }

    private StringBuilder toSBuilder(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String res = "";
        while ((res = br.readLine()) != null) {
            sb.append(res);
        }
        return sb;
    }
}
