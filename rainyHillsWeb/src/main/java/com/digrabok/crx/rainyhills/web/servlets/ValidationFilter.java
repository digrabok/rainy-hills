package com.digrabok.crx.rainyhills.web.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // pass
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new ValidatingHttpRequest( (HttpServletRequest) servletRequest ), servletResponse);
    }

    @Override
    public void destroy() {
        // pass
    }
}
