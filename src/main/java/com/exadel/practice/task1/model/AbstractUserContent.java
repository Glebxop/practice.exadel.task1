package com.exadel.practice.task1.model;

public abstract class AbstractUserContent {

     int id;
     User user;
     String title;

    public AbstractUserContent() {
    }


    public String getTitle(){
       return title;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
