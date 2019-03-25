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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
public class SignInServletTest {
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    private String login = "Admin";
    private String password = "Admin";
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
        new SignInServlet().doGet(request, response);
        verify(request, atLeastOnce()).getRequestDispatcher(anyString());
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    public void whenValidCredentional() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        new SignInServlet().doPost(request, response);
        verify(session, atLeastOnce()).setAttribute("currentUser", validate.getByCredentional(login, password));
        verify(session, atLeastOnce()).setAttribute("access", true);
        verify(response, atLeastOnce()).sendRedirect(String.format("%s/Index.html", request.getContextPath()));
        verify(request, never()).setAttribute("result", eq(anyString()));
        verify(request, never()).getRequestDispatcher(anyString());
        verify(dispatcher, never()).forward(request, response);
    }

    @Test
    public void whenInvalidCredentional() throws ServletException, IOException {
        when(request.getParameter("login")).thenReturn("sdjch");
        when(request.getParameter("password")).thenReturn("sidchyg");
        when(request.getRequestDispatcher(eq("/WEB-INF/Status.jsp"))).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        new SignInServlet().doPost(request, response);
        verify(session, never()).setAttribute("currentUser", validate.getByCredentional(login, password));
        verify(session, never()).setAttribute("access", true);
        verify(response, never()).sendRedirect(String.format("%s/", request.getContextPath()));
        verify(request, atLeastOnce()).setAttribute(eq("result"), eq("Invalid credentional!"));
        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/Status.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}