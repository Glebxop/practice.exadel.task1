package com.exadel.practice.usercontent.mains;

import com.exadel.practice.usercontent.file.properties.UserContentSecurityFactory;
import com.exadel.practice.usercontent.model.User;

public class MainProperties {
    public static void main(String[] arg) throws Exception {

        User userId123 = new User(123, "Kolia", "Kolia@gmail.com");
        User userId1234 = new User(12, "Kolia", "Kolia@gmail.com");
        UserContentSecurityFactory userContentSecurityFactory=new UserContentSecurityFactory("src/main/resources/Properties.properties");


       try {
           System.out.println(userContentSecurityFactory.createAttacment(userId1234,45,56.7,"Title"));
            System.out.println(userContentSecurityFactory.createAnnotation(userId123,233,"TextString","StringTitle"));

        } catch (SecurityException e) {
           System.out.println(e.getMessage());
        }


    }
}
