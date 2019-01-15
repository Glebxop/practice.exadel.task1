package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import java.sql.*;

public abstract class AbstractDaoDb  {

    protected ConnectionsPool connectionsPool;
    protected ResultSet resultSet = null;
    protected Connection connection = null;
    protected String SQLdell;
    protected String SQLadd;
    protected String SQLup;
    protected String SQLget;
    protected String SQLhasNext;


    protected AbstractDaoDb() {
        connectionsPool = ConnectionsPool.getConnectionsPool();
    }


    protected PreparedStatement getStatement(String sql) throws ConnectionExeption, SQLException {
        connection = connectionsPool.getConnect();
        return connection.prepareStatement(sql);
    }

    public boolean hasNext() throws DaoExcepton {
        try {


            if (resultSet == null) {

                PreparedStatement statement = getStatement(SQLhasNext);
                resultSet = statement.executeQuery();
            }
            if (resultSet.next()) {
                return true;
            } else {
                resultSet.close();
                connectionsPool.closeConnection(connection);
                return false;
            }}catch (SQLException e){throw new DbException("hasNext failed");}

    }

}
