package ru.job4j.crud.servlets.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.servlets.Validate;
import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AuthFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        User user = (User) session.getAttribute("currentUser");
        if (user == null && (!req.getRequestURI().contains("/signin") && !req.getRequestURI().contains("/createUser"))) {
            ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        try {
            logic.close();
            LOGGER.info("Close connection pool....");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}


