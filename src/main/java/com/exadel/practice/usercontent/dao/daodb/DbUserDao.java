package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;

import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbUserDao implements Dao<User> {
    private ConnectionsPool connectionsPool;
    private ResultSet resultSet = null;
    private Connection connection = null;

    public DbUserDao() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }

    @Override
    public boolean add(User user) {

        try {
            connection = connectionsPool.getConnect();
            String SQL = "insert into  users ( name , email) Values (?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionsPool.closeConnection(connection);
        }

   return false; }

    @Override
    public boolean dell(int id) {

        String SQL = "DELETE FROM users where id= ?;";
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());return false;
        } catch (Exception e) {
            e.printStackTrace();return false;
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public boolean update(User user) {

        try {
            connection = connectionsPool.getConnect();
            String SQL = "update users set  users.name=? , users.email= ? where users.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());return false;
        } catch (Exception e) {
            e.printStackTrace();return false;
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public User get(int id) {
        User user = null;

        String SQL = "select * from users where id= ?;";
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {


                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                user = new User(id, name, email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return user;
    }

    public String read() {
        int idAnn = 0;
        String name =null;
        String email=null;
        try {
            idAnn = resultSet.getInt("id");
            name = resultSet.getString("name");
             email = resultSet.getString("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "id=" + idAnn + " name=" + name + " email= " + email;

    }

    public boolean hasNext() throws Exception {

        String SQL = "select * from users;";
        Statement statement;
        if (resultSet == null) {
            connection = connectionsPool.getConnect();
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
