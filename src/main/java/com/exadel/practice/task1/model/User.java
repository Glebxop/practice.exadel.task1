package com.exadel.practice.task1.model;

import com.exadel.practice.task1.actions.Showable;
import com.exadel.practice.task1.file.properties.UserContentAccept;

public class User implements Showable {



    private int id;
    private String name;
    private String email;
    private UserContentAccept userContentAccept;

    public User() {
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        userContentAccept=new UserContentAccept();
    }


   public void write(AbstractUserContent abstractUserContent){
        userContentAccept.write(id,abstractUserContent);
   }




    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void show() {
        System.out.println( this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
