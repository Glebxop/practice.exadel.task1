package com.exadel.practice.usercontent.dao.daocsv;


import com.exadel.practice.usercontent.Exception.DaoExcepton;
import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.AbstractUserContent;
import com.exadel.practice.usercontent.model.Comment;
import com.exadel.practice.usercontent.model.User;

import java.io.*;


public class CsvCommentDao extends AbstractDaoCsv implements Dao<Comment> {


    public CsvCommentDao(String pathName) {
        super(pathName);
    }


    @Override
    public boolean add(Comment comment) throws DaoExcepton {
        return super.add(comment);
    }

    @Override
    public boolean update(Comment comment) throws DaoExcepton {
        return super.update(comment);
    }


    @Override
    public Comment get(int id) {
        Comment comment = null;
        String[] nextLine;
        try {

            while ((nextLine = csvReader.readNext()) != null) {
                if (Integer.valueOf(nextLine[0]) == id) {
                    return new Comment(id, new User(Integer.valueOf(nextLine[1]), nextLine[2], nextLine[3]), nextLine[5], nextLine[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comment;
    }

    @Override
    protected String[] getArrayfromUserCont(AbstractUserContent abstractUserContent) {
        Comment comment = (Comment) abstractUserContent;
        String[] arrComment = new String[6];
        arrComment[0] = String.valueOf(comment.getId());
        arrComment[1] = String.valueOf(comment.getUser().getId());
        arrComment[2] = comment.getUser().getName();
        arrComment[3] = comment.getUser().getEmail();
        arrComment[4] = comment.getText();
        arrComment[5] = comment.getTitle();
        return arrComment;
    }

}

