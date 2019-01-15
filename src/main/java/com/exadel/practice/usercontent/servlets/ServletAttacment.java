package com.exadel.practice.usercontent.servlets;

import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.daodb.DbAttacmentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Attachment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletAttacment extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            chousenWhatToDo(req,resp,new DbAttacmentDao(),new Attachment());
        } catch (DaoExcepton daoExcepton) {
            daoExcepton.printStackTrace();
        }
    }

    @Override
    protected AbstractUserContent creatModel(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        return new Attachment(id,setUser(req),title,Math.random());
    }
}
