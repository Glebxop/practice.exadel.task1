package com.exadel.practice.usercontent.servlets;

import au.com.bytecode.opencsv.CSVReader;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daodb.DbCommentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public abstract class AbstractServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void getModel(HttpServletRequest req, HttpServletResponse resp, Dao dao, AbstractUserContent abstractUserContent) throws IOException, DaoExcepton {

        abstractUserContent = (AbstractUserContent) dao.get(Integer.parseInt(req.getParameter("id")));
        if (abstractUserContent != null) {
            req.getSession().setAttribute("idObj", abstractUserContent.getId());
            req.getSession().setAttribute("userId", abstractUserContent.getUser().getId());
            req.getSession().setAttribute("text", abstractUserContent.getTitle());
            resp.sendRedirect("views/show.jsp");
        }
    }

    protected void addModel(HttpServletRequest req, HttpServletResponse resp, Dao dao) throws DaoExcepton, IOException {

        successOrNo(dao.add(creatModel(req)), resp);
    }

    protected void upModel(HttpServletRequest req, HttpServletResponse resp, Dao dao) throws DaoExcepton, IOException {
        successOrNo(dao.update(creatModel(req)), resp);
    }

    protected void dellModel(HttpServletRequest req, HttpServletResponse resp, Dao dao) throws DaoExcepton, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        successOrNo(dao.dell(id), resp);
    }

    protected void workWithFileSucces(HttpServletRequest req, HttpServletResponse resp, Dao dao) throws IOException {
        successOrNo(workWithFile(req), resp);
    }

    protected void chousenWhatToDo(HttpServletRequest req, HttpServletResponse resp, Dao dao, AbstractUserContent abstractUserContent) throws IOException, DaoExcepton {
        switch (req.getParameter("command")) {
            case "getComment": {
                getModel(req, resp, dao, abstractUserContent);
                break;
            }
            case "addComment": {
                addModel(req, resp, dao);
                break;
            }
            case "dellComment": {
                dellModel(req, resp, dao);
                break;
            }
            case "upComment": {
                upModel(req, resp, dao);
                break;
            }
            case "file": {
                workWithFileSucces(req, resp, dao);
                break;
            }
        }
    }


    private void successOrNo(boolean boolea, HttpServletResponse resp) throws IOException {
        if (boolea) {
            resp.sendRedirect("views/succes.jsp");
        } else resp.sendRedirect("views/sorry.jsp");

    }


    protected User setUser(HttpServletRequest req) {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        return new User((Integer) req.getSession().getAttribute("id"), name, email);
    }

    abstract protected AbstractUserContent creatModel(HttpServletRequest req);

    private boolean workWithFile(HttpServletRequest req) {
        try {
            Part filePart = req.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            Reader targetReader = new InputStreamReader(fileContent);
            CSVReader csvReader = new CSVReader(targetReader);
            List<String[]> stringList = csvReader.readAll();
            DbCommentDao dbCommentDao = new DbCommentDao();
            Comment comment = null;
            for (String[] strings : stringList) {
                comment = new Comment(Integer.valueOf(strings[0]), new User(Integer.valueOf(strings[1]), strings[2], strings[3]), strings[5], strings[4]);
                dbCommentDao.add(comment);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
