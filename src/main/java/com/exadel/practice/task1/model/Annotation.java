package com.exadel.practice.task1.model;

import com.exadel.practice.task1.actions.Showable;

public class Annotation extends AbstractUserContent implements Showable {



    private int startPos;
    private int endPos;
    private String text;



    public Annotation(int id, User user, String title, String text) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.text = text;
    }

    public void show() {
        System.out.println("Printing annotation: " + this);
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
