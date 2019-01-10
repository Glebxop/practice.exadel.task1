package com.exadel.practice.usercontent.dao;


import com.exadel.practice.usercontent.Exception.CsvException;
import com.exadel.practice.usercontent.Exception.DaoExcepton;



public interface Dao<T>  {
    void add(T t) throws DaoExcepton;
    void dell(int id) throws DaoExcepton;
    void update(T t) throws DaoExcepton;
    T get(int id) throws DaoExcepton;
    boolean hasNext()throws Exception;
    String read() throws DaoExcepton;
}