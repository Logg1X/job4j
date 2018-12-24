package crud.servlet.servlets;

import crud.servlet.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        User user = (User) session.getAttribute("currentUser");
        if (user == null && (!req.getRequestURI().contains("/signin") && !req.getRequestURI().contains("/createUser"))) {
            ((HttpServletResponse) response).sendRedirect(String.format("%s/signin", req.getContextPath()));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}


