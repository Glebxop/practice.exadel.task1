package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.AbstractUserContent;
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
        return super.add(annotation);
    }

    @Override
    public boolean update(Annotation annotation) throws DaoExcepton {
        return super.update(annotation);

    }

    @Override
    public Annotation get(int id) throws DaoExcepton {
        return (Annotation) super.get(id);
    }

    @Override
    protected AbstractUserContent createAbstrUserContFromResultSet(ResultSet resultSet) throws DbException {
        try {
            if (resultSet.next()) {
            int idAnn = resultSet.getInt("id");
            int idUser = resultSet.getInt("idUser");
            String text = resultSet.getString("text");
            String title = resultSet.getString("title");
            return new Annotation(idAnn, new User(idUser, "", ""), title, text);
        }} catch (SQLException e) {
            throw new DbException(e.getMessage());
        }throw new DbException("get failed");
    }

    @Override
    protected boolean poorStatement(PreparedStatement statement, AbstractUserContent abstractUserContent) throws SQLException {
        Annotation annotation = (Annotation) abstractUserContent;
        statement.setInt(1, annotation.getUser().getId());
        statement.setString(2, annotation.getTitle());
        statement.setString(3, annotation.getText());
        statement.executeUpdate();
        return true;
    }

    @Override
    protected boolean poorStatementUp(PreparedStatement statement, AbstractUserContent abstractUserContent) throws SQLException {
        Annotation annotation = (Annotation) abstractUserContent;

        statement.setInt(1, annotation.getUser().getId());
        statement.setString(2, annotation.getTitle());
        statement.setString(3, annotation.getText());
        statement.setInt(4, annotation.getId());
        statement.executeUpdate();
        return true;
    }

    public String read() throws DbException {
        return super.read();
    }


}
