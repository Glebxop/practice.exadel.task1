package com.exadel.practice.usercontent.dao.daodb;


import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
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
    public boolean add(Comment comment) throws DaoExcepton {
        return super.add(comment);
    }

    @Override
    public boolean update(Comment comment) throws DaoExcepton {
        return super.update(comment);
    }

    @Override
    public Comment get(int id) throws DaoExcepton {
        return (Comment) super.get(id);
    }

    @Override
    protected AbstractUserContent createAbstrUserContFromResultSet(ResultSet resultSet) throws DbException {
        try {
            if (resultSet.next()) {
                int idAnn = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String text = resultSet.getString("text");
                String title = resultSet.getString("title");
                return new Comment(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        throw new DbException("get failed");
    }

    @Override
    protected boolean poorStatement(PreparedStatement statement, AbstractUserContent abstractUserContent) throws SQLException {
        Comment annotation = (Comment) abstractUserContent;
        statement.setInt(1, annotation.getUser().getId());
        statement.setString(2, annotation.getTitle());
        statement.setString(3, annotation.getText());
        statement.executeUpdate();
        return true;
    }

    @Override
    protected boolean poorStatementUp(PreparedStatement preparedStatement, AbstractUserContent abstractUserContent) throws SQLException {
        Comment comment = (Comment) abstractUserContent;
        preparedStatement.setInt(1, comment.getUser().getId());
        preparedStatement.setString(2, comment.getText());
        preparedStatement.setString(3, comment.getTitle());
        preparedStatement.setInt(4, comment.getId());
        preparedStatement.executeUpdate();
        return true;
    }

    public String read() throws DbException {
        return super.read();

    }


}

