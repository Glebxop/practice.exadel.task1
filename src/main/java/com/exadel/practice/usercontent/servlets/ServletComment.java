package com.exadel.practice.usercontent.servlets;

import au.com.bytecode.opencsv.CSVReader;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daodb.DbCommentDao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@MultipartConfig
public class ServletComment extends AbstractServlet {
    @Override
    protected void chousenWhatToDo(HttpServletRequest req, HttpServletResponse resp, Dao dao, AbstractUserContent abstractUserContent) throws IOException, DaoExcepton {

        super.chousenWhatToDo(req, resp, dao, abstractUserContent);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            chousenWhatToDo(req,resp,new DbCommentDao(),new Comment());
        } catch (DaoExcepton daoExcepton) {
            daoExcepton.printStackTrace();
        }
    }

    @Override
    protected AbstractUserContent creatModel(HttpServletRequest req) {

        String text = req.getParameter("text");
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        return new Comment(id,setUser(req),title,text);
    }





    /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbCommentDao dbCommentDao = new DbCommentDao();

        switch (req.getParameter("command")) {
            case "getComment": {
                  Comment comment = dbCommentDao.get(Integer.parseInt(req.getParameter("id")));
                if (comment != null) {
                    req.getSession().setAttribute("idObj", comment.getId());
                    req.getSession().setAttribute("userId", comment.getUser().getId());
                    req.getSession().setAttribute("text", comment.getText());
                    resp.sendRedirect("views/show.jsp");
                }
                break;
            }
            case "addComment": {
                successOrNo(dbCommentDao.add(creatComm(req)), resp);
                break;
            }
            case "dellComment": {
                int id = Integer.parseInt(req.getParameter("id"));
                successOrNo(dbCommentDao.dell(id), resp);
                break;
            }
            case "upComment": {
                successOrNo(dbCommentDao.update(creatComm(req)), resp);
                break;

            }
            case "file": {
                successOrNo(workWithFile(req), resp);
                break;
            }
        }
    }

    private void successOrNo(boolean boolea, HttpServletResponse resp) throws IOException {
        if (boolea) {
            resp.sendRedirect("views/succes.jsp");
        } else resp.sendRedirect("views/sorry.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private Comment creatComm(HttpServletRequest req) {
        Comment comment = null;
        try {
            HttpSession httpSession = req.getSession();

            String text = req.getParameter("text");
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            User user = new User((Integer) httpSession.getAttribute("id"), name, email);

            comment = new Comment(id, user, title, text);
        } catch (NumberFormatException e) {
        }
        return comment;
    }

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
    }*/
}
