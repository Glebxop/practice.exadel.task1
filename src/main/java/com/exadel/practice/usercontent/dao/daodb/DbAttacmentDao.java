
package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbAttacmentDao extends AbstractDaoDb implements Dao<Attachment> {

    public DbAttacmentDao() {
        super();
        super.SQLadd = "insert into  attachment (idUser, title, text) Values (?,?,?)";
        super.SQLget = "select * from attachment where id= ?;";
        super.SQLdell = "DELETE FROM attachment where id= ?;";
        super.SQLup = "update attachment set attachment.idUser= ? , attachment.text=? , attachment.title= ? where attachment.id= ? ;";
        super.SQLhasNext = "select * from attachment;";
    }


    @Override
    public boolean add(Attachment attachment) throws DaoExcepton {

        try {
            PreparedStatement statement = getStatement(SQLadd);
            statement.setInt(1, attachment.getUser().getId());
            statement.setString(2, attachment.getTitle());
            statement.setDouble(3, attachment.getFileSize());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException("add failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean dell(int id) throws DaoExcepton {

        try {
PreparedStatement preparedStatement = getStatement(SQLdell);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException(e.getMessage()+"dell failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public boolean update(Attachment attachment)throws DaoExcepton {

        try {
            PreparedStatement preparedStatement = getStatement(SQLup);
            preparedStatement.setInt(1, attachment.getUser().getId());
            preparedStatement.setDouble(2, attachment.getFileSize());
            preparedStatement.setString(3, attachment.getTitle());
            preparedStatement.setInt(4, attachment.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException(e.getMessage()+"update failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public Attachment get(int id) throws DaoExcepton {
        Attachment attachment = null;

        try {
  PreparedStatement preparedStatement =getStatement(SQLget);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idAnn = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                double text = resultSet.getDouble("fileSize");
                String title = resultSet.getString("title");
                attachment = new Attachment(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage()+"get failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return attachment;
    }

    public String read() {
        int idAnn = 0;
        int idUser = 0;
        double fileSize = 0.0;
        String title = null;
        try {
            idAnn = resultSet.getInt("id");
            idUser = resultSet.getInt("idUser");
            fileSize = Double.parseDouble(resultSet.getString("fileSize"));
            title = resultSet.getString("title");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "id=" + idAnn + " idUser=" + idUser + " file size= " + fileSize + " title= " + title;

    }


}
