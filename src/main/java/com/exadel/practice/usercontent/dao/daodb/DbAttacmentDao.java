
package com.exadel.practice.usercontent.dao.daodb;


import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;

import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbAttacmentDao extends AbstractDaoDb implements Dao<Attachment> {

    public DbAttacmentDao() {
        super();
        super.SQLadd = "insert into  attachment (idUser, title,fileSize) Values (?,?,?)";
        super.SQLget = "select * from attachment where id= ?;";
        super.SQLdell = "DELETE FROM attachment where id= ?;";
        super.SQLup = "update attachment set attachment.idUser= ? , attachment.fileSize=? , attachment.title= ? where attachment.id= ? ;";
        super.SQLhasNext = "select * from attachment;";
    }


    @Override
    public boolean add(Attachment attachment) throws DaoExcepton {
        return super.add(attachment);
    }

    public boolean update(Attachment attachment) throws DaoExcepton {
        return super.update(attachment);

    }

    @Override
    public Attachment get(int id) throws DaoExcepton {
        return (Attachment) super.get(id);
    }

    @Override
    protected AbstractUserContent createAbstrUserContFromResultSet(ResultSet resultSet) throws DbException {
        try {
            if (resultSet.next()) {
                int idAnn = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                double text = resultSet.getDouble("fileSize");
                String title = resultSet.getString("title");
                return new Attachment(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        throw new DbException("get failed");
    }


    @Override
    protected boolean poorStatement(PreparedStatement statement, AbstractUserContent abstractUserContent) throws SQLException {
        Attachment attachment = (Attachment) abstractUserContent;
        statement.setInt(1, attachment.getUser().getId());
        statement.setString(2, attachment.getTitle());
        statement.setDouble(3, attachment.getFileSize());
        statement.executeUpdate();
        return true;
    }

    @Override
    protected boolean poorStatementUp(PreparedStatement preparedStatement, AbstractUserContent abstractUserContent) throws SQLException {
        Attachment attachment = (Attachment) abstractUserContent;
        preparedStatement.setInt(1, attachment.getUser().getId());
        preparedStatement.setDouble(2, attachment.getFileSize());
        preparedStatement.setString(3, attachment.getTitle());
        preparedStatement.setInt(4, attachment.getId());
        preparedStatement.executeUpdate();
        return true;
    }

    public String read() throws DbException {
        return super.read();
    }
}
