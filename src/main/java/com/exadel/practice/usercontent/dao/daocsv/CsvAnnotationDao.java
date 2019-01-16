package com.exadel.practice.usercontent.dao.daocsv;


import com.exadel.practice.usercontent.Exception.CsvException;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Annotation;

import com.exadel.practice.usercontent.model.User;

import java.io.*;


public class CsvAnnotationDao extends AbstractDaoCsv implements Dao<Annotation> {

    public CsvAnnotationDao(String pathName) {
        super(pathName);
    }


    @Override
    public boolean add(Annotation annotation) throws DaoExcepton {
        return super.add(annotation);
    }

    @Override
    public boolean update(Annotation annotation) throws DaoExcepton {
        return super.update(annotation);
    }


    @Override
    public Annotation get(int id) throws CsvException {
        Annotation annotation = null;
        String[] nextLine;
        try {
            initalizeCsvReader();
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return new Annotation(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], nextLine[4]);
                }
            }
        } catch (IOException e) {
            throw new CsvException(e.getMessage() + "get filed");
        }

        return annotation;
    }

    protected String[] getArrayfromUserCont(AbstractUserContent abstractUserContent) {
        Annotation annotation = (Annotation) abstractUserContent;
        String[] arrComment = new String[6];
        arrComment[0] = String.valueOf(annotation.getId());
        arrComment[1] = String.valueOf(annotation.getUser().getId());
        arrComment[2] = annotation.getUser().getName();
        arrComment[3] = annotation.getUser().getEmail();
        arrComment[4] = annotation.getText();
        arrComment[5] = annotation.getTitle();
        return arrComment;
    }
}
