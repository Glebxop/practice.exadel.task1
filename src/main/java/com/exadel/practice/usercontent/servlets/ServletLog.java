package com.exadel.practice.usercontent.servlets;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.dao.daodb.DbUserDao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServletLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch (req.getParameter("command")) {
            case "login": {
                int logOrno = login(req.getParameter("name"));
                if (logOrno != -1) {
                    req.getSession().setAttribute("id", logOrno);
                    resp.sendRedirect("views/jspchoise.jsp");
                } else resp.sendRedirect("index.html");
                break;
            }
            case "Registration": {
                DbUserDao dbUserDao = new DbUserDao();
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                dbUserDao.add(new User(1, name, email));
                req.getSession().setAttribute("id", login(name));
                resp.sendRedirect("views/jspchoise.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    private int login(String name) {


        String SQL = "select * from users where name = ?;";
        ConnectionsPool connectionsPool = ConnectionsPool.getConnectionsPool();
        Connection connection = null;
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return Integer.parseInt(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ConnectionExeption connectionExeption) {
            connectionExeption.printStackTrace();
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return -1;
    }
}
