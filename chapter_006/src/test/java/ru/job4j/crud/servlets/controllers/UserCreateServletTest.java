package ru.job4j.crud.servlets.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.servlets.StoresException;
import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;

    private Validate validate;

    @Before
    public void setUp() throws Exception {
        validate = ValidateStub.getInstance();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        new UserCreateServlet().doGet(request, response);
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/CreateUser.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    public void whenCreateSuccessfully() throws ServletException, IOException {
        var param = Map.of(
                "name", new String[]{"UserForTest"},
                "login", new String[]{"UserForTest"},
                "password", new String[]{"UserForTest"},
                "email", new String[]{"UserForTest@UserForTest.UserForTest"}
        );
        String result = "User added successfully!";
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(param);
        new UserCreateServlet().doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("result", result);
        verify(request, never()).setAttribute("result", "error: incorrect request");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher).forward(request, response);
        assertThat(validate.findById(Map.of("id", new String[]{"4"})).getLogin(), is("UserForTest"));
    }

    @Test
    public void whenCreateIsNotSuccessfullyThanLoginExist() throws ServletException, IOException {
        var param = Map.of(
                "name", new String[]{"UserForTest"},
                "login", new String[]{"Admin"},
                "password", new String[]{"UserForTest"},
                "email", new String[]{"UserForTest@UserForTest.UserForTest"}
        );
        String result = "login already exists!";
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(param);
        new UserCreateServlet().doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("result", result);
        verify(request, never()).setAttribute("result", "error: incorrect request");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCreateIsNotSuccessfullyThanMailIsExists() throws ServletException, IOException {
        var param = Map.of(
                "name", new String[]{"UserForTest"},
                "login", new String[]{"UForTest"},
                "password", new String[]{"UserForTest"},
                "email", new String[]{"Admin@Admin.Admin"}
        );
        String result = "A user with this email exists!";
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(param);
        new UserCreateServlet().doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("result", result);
        verify(request, never()).setAttribute("result", "error: incorrect request");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCreateIsNotSuccessfullyThanMailIsIncorrect() throws ServletException, IOException {
        var param = Map.of(
                "name", new String[]{"UserForTest"},
                "login", new String[]{"UForTest"},
                "password", new String[]{"UserForTest"},
                "email", new String[]{"Admi"}
        );
        String result = "email is incorrect!";
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(param);
        new UserCreateServlet().doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("result", result);
        verify(request, never()).setAttribute("result", "error: incorrect request");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher).forward(request, response);
    }
}