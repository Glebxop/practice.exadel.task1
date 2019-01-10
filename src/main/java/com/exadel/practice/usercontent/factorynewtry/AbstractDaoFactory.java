package com.exadel.practice.usercontent.factorynewtry;

import com.exadel.practice.usercontent.dao.Dao;
import com.exadel.practice.usercontent.model.*;

public abstract class AbstractDaoFactory {
   public abstract Dao<Comment> getCommentDao();
   public abstract Dao<Attachment> getAttachmentDao();
   public abstract Dao<Annotation> getAnnotationDao();
   public abstract Dao<User> getUserDao();
   public abstract Dao<Document> getDocumentDao();
}
