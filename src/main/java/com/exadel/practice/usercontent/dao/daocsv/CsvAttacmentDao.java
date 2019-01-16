package com.exadel.practice.usercontent.dao.daocsv;


import com.exadel.practice.usercontent.Exception.CsvException;
import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Attachment;

import com.exadel.practice.usercontent.model.User;

import java.io.*;


public class CsvAttacmentDao extends AbstractDaoCsv implements Dao<Attachment> {

    public CsvAttacmentDao(String pathName) {
        super(pathName);
    }


    @Override
    public boolean add(Attachment attachment) throws DaoExcepton {
        return super.add(attachment);
    }


    @Override
    public boolean update(Attachment attachment) throws DaoExcepton {
        return super.update(attachment);
    }


    @Override
    public Attachment get(int id) throws CsvException {
        String[] nextLine;
        try {
            initalizeCsvReader();
            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return new Attachment(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], Double.valueOf(nextLine[4]));
                }
            }
        } catch (IOException e) {
            throw new CsvException(e.getMessage() + "get filed");
        }

        return null;
    }

    @Override
    protected String[] getArrayfromUserCont(AbstractUserContent abstractUserContent) {
        Attachment attachment = (Attachment) abstractUserContent;
        String[] arrComment = new String[6];
        arrComment[0] = String.valueOf(attachment.getId());
        arrComment[1] = String.valueOf(attachment.getUser().getId());
        arrComment[2] = attachment.getUser().getName();
        arrComment[3] = attachment.getUser().getEmail();
        arrComment[4] = String.valueOf(attachment.getFileSize());
        arrComment[5] = attachment.getTitle();
        return arrComment;
    }

}
