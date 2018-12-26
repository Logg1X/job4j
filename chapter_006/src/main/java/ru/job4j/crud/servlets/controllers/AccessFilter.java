package ru.job4j.crud.servlets.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.servlets.ValidateService;
import ru.job4j.crud.servlets.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    private ValidateService logic = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(AccessFilter.class.getName());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String path = req.getServletPath();
        User user = (User) session.getAttribute("currentUser");
        session.setAttribute("access", logic.isAllowedaccess(user, path));
        Boolean access = (Boolean) session.getAttribute("access");
        if (user != null && String.valueOf(user.getId()).equals(req.getParameter("id")) && path.equals("/edit")) {
            access = true;
        }
        if (!access
                && user != null && !path.equals("/")
                && !path.equals("/signin")) {
            ((HttpServletResponse) response).sendError(403, "Permission deny!");
            return;
        } else {
            chain.doFilter(request, response);
        }
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
