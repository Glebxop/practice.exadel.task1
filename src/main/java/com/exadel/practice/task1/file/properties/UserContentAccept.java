package com.exadel.practice.task1.file.properties;

import com.exadel.practice.task1.model.AbstractUserContent;
import com.exadel.practice.task1.model.Annotation;
import com.exadel.practice.task1.model.Attachment;
import com.exadel.practice.task1.model.Comment;

import java.util.Arrays;
import java.util.List;

public class UserContentAccept {

    private SecurityManager securityManager;


//3 метода(комент,аттачмент,и


    public void write(int id, AbstractUserContent abstractUserContent) {
        securityManager = new SecurityManager();
        String accept = securityManager.check(id);
        List<String> stringList= Arrays.asList(accept.split(","));

       /* if (abstractUserContent.getAcceptt().equals(Accept.COMMENT)){

            if (stringList.contains("comment")){
            System.out.println(new Comment());
            }
        }

        if (Integer.valueOf(abstractUserContent.getAccept()).equals(Accept.Annotation.getValue())){

            if (stringList.contains("annotation")){
                System.out.println(new Annotation());
            }
        }

        if (Integer.valueOf(abstractUserContent.getAccept()).equals(Accept.Attachment.getValue())){

            if (stringList.contains("attacment")){
                System.out.println(new Attachment());
            }
        }


    }*/}

}
