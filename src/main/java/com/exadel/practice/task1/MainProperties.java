package com.exadel.practice.task1;

import com.exadel.practice.task1.file.properties.UserContentAccept;
import com.exadel.practice.task1.model.Attachment;
import com.exadel.practice.task1.model.User;

public class MainProperties {
    public static void main(String[]arg){
        System.out.println(new Attachment());
        UserContentAccept userContentAccept=new UserContentAccept();
        User userId123=new User(123,"Kolia","Kolia@gmail.com");
        userId123.write(new Attachment());



    }
}
