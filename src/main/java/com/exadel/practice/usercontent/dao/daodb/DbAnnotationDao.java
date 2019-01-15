package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Annotation;
import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbAnnotationDao extends AbstractDaoDb implements Dao<Annotation> {

    public DbAnnotationDao() {
        super();
        super.SQLadd = "insert into  annotation (idUser, title, text) Values (?,?,?)";
        super.SQLget = "select * from annotation where id= ?;";
        super.SQLdell = "DELETE FROM annotation where id= ?;";
        super.SQLup = "update annotation set annotation.idUser= ? , annotation.text=? , annotation.title= ? where annotation.id= ? ;";
        super.SQLhasNext = "select * from annotation;";
    }

    @Override
    public boolean add(Annotation annotation) throws DaoExcepton {

        try {
            PreparedStatement statement = getStatement(SQLadd);
            statement.setInt(1, annotation.getUser().getId());
            statement.setString(2, annotation.getTitle());
            statement.setString(3, annotation.getText());
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
        }  catch (SQLException e) {
            throw new DbException("dell failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public boolean update(Annotation annotation) throws DaoExcepton {

        try {
            PreparedStatement preparedStatement = getStatement(SQLup);
            preparedStatement.setInt(1, annotation.getUser().getId());
            preparedStatement.setString(2, annotation.getText());
            preparedStatement.setString(3, annotation.getTitle());
            preparedStatement.setInt(4, annotation.getId());
            preparedStatement.executeUpdate();
            return true;
        }  catch (SQLException e) {
            throw new DbException("update failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public Annotation get(int id) throws DaoExcepton {
        Annotation annotation = null;
        try {
            PreparedStatement preparedStatement = getStatement(SQLget);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idAnn = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String text = resultSet.getString("text");
                String title = resultSet.getString("title");
                annotation = new Annotation(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            throw new DbException("get failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return annotation;

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
            System.out.println(e.getMessage());
        }

        return "id=" + idAnn + " idUser=" + idUser + " text= " + text + " title= " + title;

    }


}
