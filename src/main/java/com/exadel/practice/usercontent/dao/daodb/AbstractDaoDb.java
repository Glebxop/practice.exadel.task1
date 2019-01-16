package com.exadel.practice.usercontent.dao.daodb;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.Exception.DbException;

import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.model.AbstractUserContent;

import java.sql.*;

public abstract class AbstractDaoDb {

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

    public boolean add(AbstractUserContent abstractUserContent) throws DaoExcepton {
        try {
            PreparedStatement statement = getStatement(SQLadd);
            return poorStatement(statement, abstractUserContent);
        } catch (SQLException e) {
            throw new DbException("add failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    public AbstractUserContent get(int id) throws DaoExcepton {

        try {
            PreparedStatement preparedStatement = getStatement(SQLget);
            preparedStatement.setInt(1, id);
            return createAbstrUserContFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DbException("get failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

    protected abstract AbstractUserContent createAbstrUserContFromResultSet(ResultSet resultSet) throws DbException;


    protected abstract boolean poorStatement(PreparedStatement statement, AbstractUserContent abstractUserContent) throws SQLException;


    protected abstract boolean poorStatementUp(PreparedStatement preparedStatement, AbstractUserContent abstractUserContent) throws SQLException;


    public boolean update(AbstractUserContent abstractUserContent) throws DaoExcepton {

        try {
            PreparedStatement preparedStatement = getStatement(SQLup);
            return poorStatementUp(preparedStatement, abstractUserContent);
        } catch (SQLException e) {
            throw new DbException("update failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }


    protected PreparedStatement getStatement(String sql) throws ConnectionExeption, SQLException {
        connection = connectionsPool.getConnect();
        return connection.prepareStatement(sql);
    }

    public String read() throws DbException {


        return createAbstrUserContFromResultSet(resultSet).toString();


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
                return false;
            }
        } catch (SQLException e) {
            throw new DbException("hasNext failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }

    }

    public boolean dell(int id) throws DaoExcepton {
        try {
            PreparedStatement preparedStatement = getStatement(SQLdell);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DbException("dell failed");
        } finally {
            connectionsPool.closeConnection(connection);
        }
    }

}
