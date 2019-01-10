package com.exadel.practice.usercontent.file.properties;

import com.exadel.practice.usercontent.model.*;
public class UserContentSecurityFactory {

    private SecurityManager securityManager;

    public UserContentSecurityFactory(String filePath) {
        securityManager=new SecurityManager(filePath);
    }

    public Comment createComment(User user, int idComment, String text, String title) throws Exception {
         if (securityManager.check(user.getId(),"comment")) {
            return new Comment(idComment, user, title, text);
        } else {

            throw new SecurityException("User with id "+user.getId()+" don't have permission write comment");
        }

    }
    public Attachment createAttacment(User user, int idComment, Double fileSize, String title) throws Exception {
        if (securityManager.check(user.getId(),"attachment")) {
            return new Attachment(idComment, user, title, fileSize);
        } else {

            throw new SecurityException("User with id "+user.getId()+" don't have permission write attachment");
        }

    }
    public Annotation createAnnotation(User user, int idComment, String text, String title) throws Exception {
        if (securityManager.check(user.getId(),"annotation")) {
            return new Annotation(idComment, user, title, text);
        } else {

            throw new SecurityException("User with id "+user.getId()+" don't have permission write annotation");
        }

    }
}
