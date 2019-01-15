package com.exadel.practice.usercontent.filters;

import com.exadel.practice.usercontent.file.properties.SecurityManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AnnotationFilter extends AbstractFilter {


    @Override
    void chooseJsp(SecurityManager securityManager, HttpSession session, ServletResponse servletResponse) throws IOException {
        if (securityManager.check((int) session.getAttribute("id"), "annotation")) {

            ((HttpServletResponse) servletResponse).sendRedirect("views/annotation.jsp");
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("views/annotationUser.jsp");
        }
    }

}