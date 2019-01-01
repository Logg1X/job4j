package ru.job4j.crud.servlets.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.ValidateStub;
import ru.job4j.crud.servlets.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserTableServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    Validate validate;

    @Before
    public void setUp() throws Exception {
        validate = ValidateStub.getInstance();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        List<User> users = validate.findAll();
        when(request.getRequestDispatcher("WEB-INF/listUsr.jsp")).thenReturn(dispatcher);
        new UserTableServlet().doGet(request, response);
        verify(request).setAttribute("users", users);
        verify(request, atLeastOnce()).getRequestDispatcher("WEB-INF/listUsr.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}