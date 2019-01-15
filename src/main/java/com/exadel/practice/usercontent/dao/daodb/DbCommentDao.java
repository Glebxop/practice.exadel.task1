package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import java.sql.*;

public class DbCommentDao extends AbstractDaoDb implements Dao<Comment> {

    public DbCommentDao() {
        super();
        super.SQLadd = "insert into  comment (idUser, title, text) Values (?,?,?)";
        super.SQLget = "select * from comment where id= ?;";
        super.SQLdell = "DELETE FROM comment where id= ?;";
        super.SQLup = "update comment set comment.idUser= ? , comment.text=? , comment.title= ? where comment.id= ? ;";
        super.SQLhasNext = "select * from comment;";
    }

    @Override
    public boolean add(Comment comment) throws ConnectionExeption, DbException {

        try {

            PreparedStatement statement = getStatement(SQLadd);
            statement.setInt(1, comment.getUser().getId());
            statement.setString(2, comment.getTitle());
            statement.setString(3, comment.getText());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException("add failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean dell(int id) throws ConnectionExeption, DbException {
        try {

            PreparedStatement preparedStatement = getStatement(SQLdell);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException("dell failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean update(Comment comment) throws ConnectionExeption, DbException {

        try {

            PreparedStatement preparedStatement = getStatement(SQLup);
            preparedStatement.setInt(1, comment.getUser().getId());
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setString(3, comment.getTitle());
            preparedStatement.setInt(4, comment.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException("update failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public Comment get(int id) throws DaoExcepton {
        Comment comment = null;

        try {

            PreparedStatement preparedStatement = getStatement(SQLget);
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
            throw new DbException("get failed");
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



}

