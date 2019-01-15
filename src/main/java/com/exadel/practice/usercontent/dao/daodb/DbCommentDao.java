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
    public boolean add(Comment comment) {

        try {
            connection = connectionsPool.getConnect();

            String SQL = "insert into  comment (idUser, title, text) Values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, comment.getUser().getId());
            statement.setString(2, comment.getTitle());
            statement.setString(3, comment.getText());
            statement.executeUpdate();
            return true;
        } catch (ConnectionExeption exeption) {
            System.out.println(exeption.getMessage());
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean dell(int id) {

        String SQL = "DELETE FROM comment where id= ?;";
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (ConnectionExeption exeption) {
            System.out.println(exeption.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean update(Comment comment) {

        try {
            connection = connectionsPool.getConnect();
            String SQL = "update comment set comment.idUser= ? , comment.text=? , comment.title= ? where comment.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setString(3, comment.getTitle());
            preparedStatement.setInt(4, comment.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (ConnectionExeption exeption) {
            System.out.println(exeption.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public Comment get(int id) {
        Comment comment = null;

        String SQL = "select * from comment where id= ?;";
        try {
            connection = connectionsPool.getConnect();
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
        } catch (ConnectionExeption exeption) {
            System.out.println(exeption.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return comment;
    }

    public String read() {
        int idAnn = 0;
        int idUser = 0;
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
            } catch (ConnectionExeption exeption) {
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

