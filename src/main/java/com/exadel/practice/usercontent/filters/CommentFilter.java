package com.exadel.practice.usercontent.filters;

import com.exadel.practice.usercontent.file.properties.SecurityManager;

import javax.servlet.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class CommentFilter extends AbstractFilter {

    @Override
    void chooseJsp(SecurityManager securityManager, HttpSession session, ServletResponse servletResponse) throws IOException {
        if (securityManager.check((int)session.getAttribute("id"),"comment")){

            ((HttpServletResponse) servletResponse).sendRedirect("views/comments.jsp");
        }else {
            ((HttpServletResponse) servletResponse).sendRedirect("views/commentsUser.jsp");
        }
    }


}
