package com.exadel.practice.usercontent.dao;

import com.exadel.practice.usercontent.Exception.ConnectionExeption;
import com.exadel.practice.usercontent.Exception.DaoExcepton;



public interface Dao<T>  {
    boolean add(T t) throws DaoExcepton;
    boolean dell(int id) throws DaoExcepton;
    boolean update(T t) throws DaoExcepton;
    T get(int id) throws DaoExcepton;
    boolean hasNext()throws Exception;
    String read() throws DaoExcepton;
}