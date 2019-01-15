package com.exadel.practice.usercontent.servlets;

import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.daodb.DbAttacmentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Attachment;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletAttacment extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Attachment attachment = null;
        try {
            chousenWhatToDo(req, resp, new DbAttacmentDao(), attachment);
        } catch (DaoExcepton daoExcepton) {
            resp.getWriter().write(daoExcepton.getMessage());
        }
    }

    @Override
    protected AbstractUserContent creatModel(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        return new Attachment(id, setUser(req), title, Math.random());
    }
}
