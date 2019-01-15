package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.Document;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDocumentDao implements Dao<Document> {

    private ConnectionsPool connectionsPool;
    private ResultSet resultSet = null;
    private Connection connection = null;

    public DbDocumentDao() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }

    @Override
    public boolean add(Document document) {

        try {
            try {
                connection = connectionsPool.getConnect();
            } catch (ConnectionExeption exeption) {
                System.out.println(exeption.getMessage());
            }
            String SQL = "insert into  document (id, title, text) Values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, document.getId());
            statement.setString(2, document.getTitle());
            statement.setString(3, document.getText());
            statement.executeUpdate();
            String SQLAtt = "insert into  listdocument (idDocument, idUser , title , fileSize) Values (?,?,?,?)";
            for (Attachment attachment : document.getUserContentList()) {
                PreparedStatement preparedStatementListAtt = connection.prepareStatement(SQLAtt);
                preparedStatementListAtt.setInt(1, document.getId());
                preparedStatementListAtt.setInt(2, attachment.getUser().getId());
                preparedStatementListAtt.setString(3, attachment.getTitle());
                preparedStatementListAtt.setDouble(4, attachment.getFileSize());
                preparedStatementListAtt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean dell(int id) {

        String SQL = "DELETE FROM document where id= ?;";
        String SQLList = "DELETE FROM listdocument where idDocument= ?;";
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatementList = connection.prepareStatement(SQLList);
            preparedStatementList.setInt(1, id);
            preparedStatementList.executeUpdate();
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
    public boolean update(Document document) {

        try {
            connection = connectionsPool.getConnect();
            String SQL = "update document set document.title= ? , document.text=?  where comment.id= ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, document.getTitle());
            preparedStatement.setString(2, document.getText());
            preparedStatement.setInt(3, document.getId());
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
    public Document get(int id) {
        Document document = null;

        String SQL = "select * from document where id= ?;";
        String SQLlist = "select * from listdocument where idDocument= ?;";
        try {
            connection = connectionsPool.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            PreparedStatement preparedStatementList = connection.prepareStatement(SQLlist);
            preparedStatement.setInt(1, id);
            preparedStatementList.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idDoc = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                List<Attachment> attachmentList = new ArrayList<>();
                ResultSet resultSetList = preparedStatementList.executeQuery();
                while (resultSetList.next()) {

                    String titleAtt = resultSetList.getString("title");
                    Double fileSize = resultSetList.getDouble("fileSize");
                    attachmentList.add(new Attachment(titleAtt, id, fileSize));
                }

                document = new Document(idDoc, title, text, attachmentList);
            }
        } catch (ConnectionExeption exeption) {
            System.out.println(exeption.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connectionsPool.closeConnection(connection);
        }
        return document;
    }

    public String read() {


        String SQLlist = "select * from listdocument where idDocument= ?;";
        PreparedStatement preparedStatementList = null;
        List<Attachment> attachmentList = null;
        int idDoc = 0;
        String title = null;
        String text = null;
        try {
            try {
                connection = connectionsPool.getConnect();
            } catch (ConnectionExeption exeption) {
                System.out.println(exeption.getMessage());
            }
            preparedStatementList = connection.prepareStatement(SQLlist);
            idDoc = resultSet.getInt("id");
            title = resultSet.getString("title");
            text = resultSet.getString("text");
            attachmentList = new ArrayList<>();
            preparedStatementList.setInt(1, idDoc);
            ResultSet resultSetList = preparedStatementList.executeQuery();
            while (resultSetList.next()) {

                String titleAtt = resultSetList.getString("title");
                Double fileSize = resultSetList.getDouble("fileSize");
                attachmentList.add(new Attachment(titleAtt, idDoc, fileSize));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new Document(idDoc, title, text, attachmentList).toString();
    }

    public boolean hasNext() throws SQLException {

        String SQL = "select * from document;";
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
