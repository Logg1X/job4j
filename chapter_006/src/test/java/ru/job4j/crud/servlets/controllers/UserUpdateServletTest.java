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
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {
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
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        User user = validate.findById(Map.of("id", new String[]{"1"}));
        when(request.getParameterMap()).thenReturn(Map.of("id", new String[]{"1"}));
        new UserUpdateServlet().doGet(request, response);
        verify(request, atLeastOnce()).setAttribute("result", "");
        verify(request, atLeastOnce()).setAttribute("user", user);
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(dispatcher, atLeastOnce()).forward(request, response);

    }

    @Test
    public void doPost() throws ServletException, IOException {
        var param = Map.of(
                "id", new String[]{"1"},
                "name", new String[]{"Admin1"},
                "login", new String[]{"Admin1"},
                "email", new String[]{"Admin@Admin.Admin1"},
                "password", new String[]{"Admin1"},
                "role", new String[]{"ADMIN"},
                "createDate", new String[]{"2018-12-30T17:20:30.245229"}
        );
        when(request.getParameterMap()).thenReturn(param);
        when(request.getRequestDispatcher("/WEB-INF/Status.jsp")).thenReturn(dispatcher);
        User user = validate.update(param);
        new UserUpdateServlet().doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("user", validate.findById(param));
        verify(request, atLeastOnce()).setAttribute("result", "Update successfully!");
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
        assertThat(validate.findById(Map.of("id", new String[]{"1"})).getLogin(), is("Admin1"));
    }
}