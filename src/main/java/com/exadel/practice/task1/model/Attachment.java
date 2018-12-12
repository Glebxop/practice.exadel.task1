package com.exadel.practice.task1.model;


import com.exadel.practice.task1.actions.Downloadeble;

public class Attachment extends AbstractUserContent implements Downloadeble, Comparable<Attachment> {



    private double fileSize;


    public Attachment(int id, User user, String title, double fileSize) {
        super(id, user, title);
        this.fileSize = fileSize;
    }

    public void download(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public int compareTo(Attachment o) {


        return Double.compare(fileSize, o.getFileSize());
    }
}
