package com.exadel.practice.usercontent.model;

public abstract class AbstractUserContent {

    protected int id;
    protected User user;
    protected String title;


    public AbstractUserContent() {
    }

    public AbstractUserContent(int id, User user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;

    }

    public String getTitle() {
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

    @Override
    public String toString() {
        return "AbstractUserContent{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                '}';
    }
}
