package com.exadel.practice.usercontent.factorydao;

import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.dao.daodb.*;
import com.exadel.practice.usercontent.model.*;

public class DbDaoFactory extends AbstractDaoFactory {
    @Override
    public  Dao<Comment> getCommentDao() {
        return new DbCommentDao();
    }

    @Override
    public  Dao<Attachment> getAttachmentDao() {
        return new DbAttacmentDao();
    }

    @Override
    public   Dao<Annotation> getAnnotationDao() {
        return new DbAnnotationDao();
    }

    @Override
    public  Dao<User> getUserDao() {
        return new DbUserDao();
    }

    @Override
    public  Dao<Document> getDocumentDao() {
        return new DbDocumentDao();
    }
}
