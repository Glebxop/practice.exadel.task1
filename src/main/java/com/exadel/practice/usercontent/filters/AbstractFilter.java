package com.exadel.practice.usercontent.filters;

import com.exadel.practice.usercontent.file.properties.SecurityManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityManager securityManager = new SecurityManager("Properties.properties");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        chooseJsp(securityManager,session,servletResponse);
    }
    abstract void chooseJsp(SecurityManager securityManager,HttpSession session,ServletResponse servletResponse) throws IOException;

    @Override
    public void destroy() {

    }
}
