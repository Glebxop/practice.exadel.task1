package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import java.sql.*;

public class DbCommentDao implements Dao<Comment> {
    private ConnectionsPool connectionsPool;
    private ResultSet resultSet = null;
    private Connection connection = null;

    public DbCommentDao() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }

    @Override
    public void add(Comment comment) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "insert into  comment (idUser, title, text) Values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, comment.getUser().getId());
            statement.setString(2, comment.getTitle());
            statement.setString(3, comment.getText());
            statement.executeUpdate();
            System.out.println("Comment added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public void dell(int id) {

        String SQL = "DELETE FROM comment where id= ?;";
        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            PreparedStatement preparedStatement=connection.prepareStatement(SQL);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Comment id=" + id + " delited");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public void update(Comment comment) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "update comment set comment.idUser= ? , comment.text=? , comment.title= ? where comment.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setString(3, comment.getTitle());
            preparedStatement.setInt(4, comment.getId());
            preparedStatement.executeUpdate();
            System.out.println("Comment updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public Comment get(int id) {
        Comment comment = null;

        String SQL = "select * from comment where id= ?;";
        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idAnn = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String text = resultSet.getString("text");
                String title = resultSet.getString("title");
                comment = new Comment(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            connectionsPool.closeConnection(connection);
        }
        return comment;
    }

    public String read()  {
        int idAnn = 0;
        int idUser =0;
        String text = null;
        String title = null;
        try {
            idAnn = resultSet.getInt("id");
            idUser = resultSet.getInt("idUser");
             text = resultSet.getString("text");
             title = resultSet.getString("title");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "id=" + idAnn + " idUser=" + idUser + " text= " + text + " title= " + title;

    }

    public boolean hasNext() throws SQLException {

        String SQL = "select * from comment;";
        Statement statement;
        if (resultSet == null) {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
        }
        if (resultSet.next()) {
            return true;
        } else {
            resultSet.close();
            connectionsPool.closeConnection(connection);
            return false;
        }

    }
}

