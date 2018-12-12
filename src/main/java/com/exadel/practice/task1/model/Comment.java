package com.exadel.practice.task1.model;


import com.exadel.practice.task1.actions.Showable;

public class Comment extends AbstractUserContent implements Showable {



    private String text;

    public Comment(int id, User user, String title, String text) {
        super(id, user, title);
        this.text = text;
    }

    public void show() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
