package com.exadel.practice.usercontent.model;


import com.exadel.practice.usercontent.actions.Showable;

public class Comment extends AbstractUserContent implements Showable {


    private String text;


    public Comment() {
    }

    public Comment(String text,int id,String title) {
        this.id=id;
        this.title=title;
        this.text = text;
    }

    public Comment(int id, User user, String title, String text) {
        super(id, user, title);
        this.text = text;
    }

    public void show() {
        System.out.println("Printing comment: " + this);
    }


    public Object deserializeFromCsv(String csvString) {
        return null;
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
