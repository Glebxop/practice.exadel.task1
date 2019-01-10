
package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.User;

import java.sql.*;


public class DbAttacmentDao implements Dao<Attachment> {

    private ConnectionsPool connectionsPool;
    private ResultSet resultSet = null;
    private Connection connection = null;

    public DbAttacmentDao() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }

    @Override
    public void add(Attachment attachment) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "insert into  attachment (idUser, title, fileSize) Values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, attachment.getUser().getId());
            statement.setString(2, attachment.getTitle());
            statement.setDouble(3, attachment.getFileSize());
            statement.executeUpdate();
            System.out.println("Attachment added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    @Override
    public void dell(int id) {

        String SQL = "DELETE FROM attachment where id= ?;";
        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Attachment id=" + id + " delited");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public void update(Attachment attachment) {

        try {
            try {
                connection = connectionsPool.getConnect();
            }catch (ConnectionExeption exeption){
                System.out.println(exeption.getMessage());
            }
            String SQL = "update attachment set attachment.idUser= ? , attachment.fileSize=? , attachment.title= ? where attachment.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, attachment.getUser().getId());
            preparedStatement.setDouble(2, attachment.getFileSize());
            preparedStatement.setString(3, attachment.getTitle());
            preparedStatement.setInt(4, attachment.getId());
            preparedStatement.executeUpdate();
            System.out.println("Attachment updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    @Override
    public Attachment get(int id) {
        Attachment attachment = null;
        String SQL = "select * from attachment where id= ?;";
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
                Double text = resultSet.getDouble("fileSize");
                String title = resultSet.getString("title");
                attachment = new Attachment(idAnn, new User(idUser, "", ""), title, text);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        return  "id=" + idAnn + " idUser=" + idUser + " file size= " + fileSize + " title= " + title;

    }

    public boolean hasNext() throws SQLException {
        String SQL = "select * from attachment;";
        Statement statement = null;
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
