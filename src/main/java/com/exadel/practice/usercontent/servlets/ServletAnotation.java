package com.exadel.practice.usercontent.servlets;

import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daodb.DbAnnotationDao;
import com.exadel.practice.usercontent.dao.daodb.DbCommentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Annotation;
import com.exadel.practice.usercontent.model.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletAnotation extends AbstractServlet {
    @Override
    protected void chousenWhatToDo(HttpServletRequest req, HttpServletResponse resp, Dao dao, AbstractUserContent abstractUserContent) throws IOException, DaoExcepton {

        super.chousenWhatToDo(req, resp, dao, abstractUserContent);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            chousenWhatToDo(req,resp,new DbAnnotationDao(),new Annotation());
        } catch (DaoExcepton daoExcepton) {
            daoExcepton.printStackTrace();
        }
    }

    @Override
    protected AbstractUserContent creatModel(HttpServletRequest req) {
        Annotation annotation=null;
        String text = req.getParameter("text");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        return new Annotation(id,setUser(req),title,text);
    }
}
