package com.exadel.practice.usercontent.mains;


import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.db.ConnectionsPool;
import com.exadel.practice.usercontent.factorynewtry.AbstractDaoFactory;
import com.exadel.practice.usercontent.factorynewtry.FactoryOfDaoFactories;

public class MainDB {
    public static void main(String[] arg) throws Exception {

      //  Launcher launcher=new Launcher();
        //launcher.start();

        ConnectionsPool connectionsPool=ConnectionsPool.getConnectionsPool();
        connectionsPool.showStatus();

        AbstractDaoFactory factoryOfDaoFactories=FactoryOfDaoFactories.getDbDaoFactory();
       Dao comment= factoryOfDaoFactories.getCommentDao();
        while (comment.hasNext()){
            System.out.println(comment.read());
        }

        connectionsPool.closeAllCon();
        connectionsPool.showStatus();
    }
}
