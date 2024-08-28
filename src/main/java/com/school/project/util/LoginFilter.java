package com.school.project.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("user") == null && !req.getRequestURI().endsWith("/login.xhtml")) {
            res.sendRedirect(req.getContextPath() + "/Login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
