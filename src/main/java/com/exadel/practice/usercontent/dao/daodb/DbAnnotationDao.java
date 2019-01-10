package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Annotation;
import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbAnnotationDao implements Dao<Annotation> {
    private ConnectionsPool connectionsPool;
    private ResultSet resultSet = null;
    private Connection connection = null;

    public DbAnnotationDao() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }

    @Override
    public void add(Annotation annotation) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "insert into  annotation (idUser, title, text) Values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, annotation.getUser().getId());
            statement.setString(2, annotation.getTitle());
            statement.setString(3, annotation.getText());
            statement.executeUpdate();
            System.out.println("Annotation added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public void dell(int id) {


        String SQL = "DELETE FROM annotation where id= ?;";
        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Annotation id=" + id + " delited");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public void update(Annotation annotation) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "update annotation set annotation.idUser= ? , annotation.text=? , annotation.title= ? where annotation.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, annotation.getUser().getId());
            preparedStatement.setString(2, annotation.getText());
            preparedStatement.setString(3, annotation.getTitle());
            preparedStatement.setInt(4, annotation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Annotation updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public Annotation get(int id) {
        Annotation annotation = null;
        String SQL = "select * from annotation where id= ?;";
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
                annotation = new Annotation(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  finally {
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

    public boolean hasNext() throws SQLException {

        String SQL = "select * from annotation;";
        Statement statement = null;
        if (resultSet == null) {
            try {
                connection = connectionsPool.getConnect();
            } catch (ConnectionExeption e) {
                System.out.println(e.getMessage());
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
