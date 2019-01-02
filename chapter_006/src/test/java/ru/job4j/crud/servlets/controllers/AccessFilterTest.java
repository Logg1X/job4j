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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class AccessFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private FilterChain chain;
    private Validate validate;

    @Before
    public void setUp() throws Exception {
        validate = ValidateStub.getInstance();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
    }

    @Test
    public void whenAccessIsNotAllowed() throws IOException, ServletException {
        User user = validate.findById(Map.of("id", new String[]{"2"}));
        when(request.getParameter("id")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/createUser");
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(session.getAttribute("access")).thenReturn(validate.isAllowedaccess(user, "/createUser"));
        new AccessFilter().doFilter(request, response, chain);
        verify(session, atLeastOnce()).setAttribute("access", validate.isAllowedaccess(user, "/createUser"));
        verify(session, atLeastOnce()).getAttribute("access");
        verify(request, atLeastOnce()).getParameter("id");
        verify(response).sendError(eq(403), eq("Permission deny!"));
    }

    @Test
    public void whenAccessIsAllowed() throws IOException, ServletException {
        User user = validate.findById(Map.of("id", new String[]{"1"}));
        when(request.getParameter("id")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/createUser");
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(session.getAttribute("access")).thenReturn(validate.isAllowedaccess(user, "/createUser"));
        new AccessFilter().doFilter(request, response, chain);
        verify(session, atLeastOnce()).setAttribute("access", validate.isAllowedaccess(user, "/createUser"));
        verify(session, atLeastOnce()).getAttribute("access");
        verify(request, atLeastOnce()).getParameter("id");
        verify(chain, atLeastOnce()).doFilter(request, response);
    }

    @Test
    public void whenAccessIsAllowedForEditThenRoleUser() throws IOException, ServletException {
        User user = validate.findById(Map.of("id", new String[]{"2"}));
        when(request.getParameter("id")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/edit");
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(session.getAttribute("access")).thenReturn(validate.isAllowedaccess(user, "/edit"));
        new AccessFilter().doFilter(request, response, chain);
        verify(session, atLeastOnce()).setAttribute("access", validate.isAllowedaccess(user, "/edit"));
        verify(session, atLeastOnce()).getAttribute("access");
        verify(request, atLeastOnce()).getParameter("id");
        verify(chain, atLeastOnce()).doFilter(request, response);
    }
}