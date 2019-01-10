package com.exadel.practice.usercontent.factorynewtry;

import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daocsv.*;
import com.exadel.practice.usercontent.model.*;

public class CsvDaoFactory extends AbstractDaoFactory {
    private String pathName;

    public CsvDaoFactory(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public Dao<Comment> getCommentDao() {
        return new CsvCommentDao(pathName);
    }

    @Override
    public  Dao<Attachment> getAttachmentDao() {
        return new CsvAttacmentDao(pathName);
    }

    @Override
    public  Dao<Annotation> getAnnotationDao() {
        return new CsvAnnotationDao(pathName);
    }

    @Override
    public  Dao<User> getUserDao() {
        return new CsvUserDao(pathName);
    }

    @Override
    public  Dao<Document> getDocumentDao() {
        return new CsvDocumentDao(pathName);
    }
}
