package com.exadel.practice.usercontent.db;


import com.exadel.practice.usercontent.Exception.ConnectionExeption;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class ConnectionsPool {

    private static ConnectionsPool connectionsPool;
    private static List<Connection> freeCon;
    private static List<Connection> buisyCon;

    private ConnectionsPool() {

        buisyCon = new ArrayList<>();
        freeCon = new ArrayList<>();
        poorPool();

    }

    public static synchronized ConnectionsPool getConnectionsPool() {
        if (connectionsPool == null) {
            connectionsPool = new ConnectionsPool();
        }
        return connectionsPool;
    }


    private void poorPool() {

        try {

            PropertyReader propertyReader = new PropertyReader();
            Properties props = propertyReader.getProperties("database.properties");
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String driver = props.getProperty("driver");
            int size = Integer.parseInt(props.getProperty("size"));
            Class.forName(driver).getDeclaredConstructor().newInstance();
            if (freeCon.size() == 0) {
                for (int i = 0; i < size; i++) {
                    freeCon.add(DriverManager.getConnection(url, username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnect() throws ConnectionExeption {
        Connection connection = null;
        if (freeCon.size() > 0) {
            connection = freeCon.remove(freeCon.size() - 1);
            buisyCon.add(connection);

        } else {
            System.out.println("Sorry");
            throw new ConnectionExeption("Sorry all connections are busy");
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        buisyCon.remove(connection);
        freeCon.add(connection);
    }

    public void showStatus() {
        System.out.println("Pool has " + freeCon.size() + " connections");
    }

    public void closeAllCon() {

        closeAll(freeCon);
        closeAll(buisyCon);

    }

    private void closeAll(List<Connection> connectionList) {
        Iterator<Connection> connectionIterator = connectionList.iterator();
        Connection connection = null;
        while (connectionIterator.hasNext()) {
            try {
                connection = connectionIterator.next();
                connection.close();
                connectionIterator.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
