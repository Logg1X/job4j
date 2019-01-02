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

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class AuthFilterTest {
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
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
    public void doFilter() throws IOException, ServletException {
        User user = validate.findById(Map.of("id", new String[]{"1"}));
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/");
        when(session.getAttribute("currentUser")).thenReturn(user);
        new AuthFilter().doFilter(request, response, chain);
        verify(session, atLeastOnce()).getAttribute("currentUser");
        verify(chain, atLeastOnce()).doFilter(request, response);
    }

    @Test
    public void whenDoFilterIsBad() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/");
        when(session.getAttribute("currentUser")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/chapter_006/listUsr");
        new AuthFilter().doFilter(request, response, chain);
        verify(session, atLeastOnce()).getAttribute("currentUser");
        verify(response, atLeastOnce()).sendRedirect(String.format("%s/signin", request.getContextPath()));
    }
}