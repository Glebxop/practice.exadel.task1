package com.exadel.practice.task1.model;


import com.exadel.practice.task1.actions.Showable;

public class Annotation extends AbstractUserContent implements Showable {



    private int startPos;
    private int endPos;
    private String text;
    final static int IDUSERCONTANT=2;




    public Annotation(int id, User user, String title, String text) {
        super(id, user, title);
        this.text = text;

    }


    public String serializeToCsv(){
        return IDUSERCONTANT+","+ id+","+user.getId()+","+user.getName()+","+user.getEmail()+","+title+","+text;

    }

    /*public Object deserializeFromCsv(String csvString){
        String[]csvStringSplit=csvString.split(",");

        return new Annotation(Integer.valueOf(csvStringSplit[0]),
                new User(Integer.valueOf(csvStringSplit[1]),csvStringSplit[2],csvStringSplit[3]),csvStringSplit[4],csvStringSplit[5]);
    }*/



    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
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
