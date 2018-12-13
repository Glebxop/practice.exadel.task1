package com.exadel.practice.task1.model;


import com.exadel.practice.task1.actions.Showable;

public class Comment extends AbstractUserContent implements Showable {



    private String text;
    final static int IDUSERCONTANT=1;

    public Comment(int id, User user, String title, String text) {
        super(id, user, title);
        this.text = text;
    }

    public void show() {
        System.out.println("Printing comment: "+this);
    }



    @Override
    public String serializeToCsv() {

        return IDUSERCONTANT+","+ id+","+user.getId()+","+user.getName()+","+user.getEmail()+","+title+","+text;

    }

    /*@Override
    public Object deserializeFromCsv(String csvString) {
        return null;
    }*/

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
