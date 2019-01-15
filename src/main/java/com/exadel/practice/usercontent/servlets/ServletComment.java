package com.exadel.practice.usercontent.servlets;


import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daodb.DbCommentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Comment;



import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;


@MultipartConfig
public class ServletComment extends AbstractServlet {
    @Override
    protected void chousenWhatToDo(HttpServletRequest req, HttpServletResponse resp, Dao dao, AbstractUserContent abstractUserContent) throws IOException, DaoExcepton {

        super.chousenWhatToDo(req, resp, dao, abstractUserContent);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Comment comment=null;
        try {
            chousenWhatToDo(req,resp,new DbCommentDao(),comment);
        } catch (DaoExcepton daoExcepton) {
            resp.getWriter().write(daoExcepton.getMessage());
        }
    }


    @Override
    protected AbstractUserContent creatModel(HttpServletRequest req) {

        String text = req.getParameter("text");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        return new Comment(id,setUser(req),title,text);
    }


}
