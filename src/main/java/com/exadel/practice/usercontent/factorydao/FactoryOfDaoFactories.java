package com.exadel.practice.usercontent.factorydao;

public class FactoryOfDaoFactories {

   public static AbstractDaoFactory getCsvDaoFactory(String path){
    return new CsvDaoFactory(path);
   }

   public static AbstractDaoFactory getDbDaoFactory(){
    return new DbDaoFactory();
   }
}
