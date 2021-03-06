package com.exadel.practice.usercontent.model;


import com.exadel.practice.usercontent.actions.Downloadeble;

public class Attachment extends AbstractUserContent implements Downloadeble, Comparable<Attachment> {


    private double fileSize;
    public Attachment(String title,int id,Double fileSize) {
        this.id=id;
        this.title=title;
        this.fileSize = fileSize;
    }


    public Attachment(int id, User user, String title, double fileSize) {
        super(id, user, title);
        this.fileSize = fileSize;
    }


    public Attachment() {
    }

    public void download() {
        System.out.println("Downloading " + this);
    }

    public String serializeToCsv() {
        return id + "," + user.getId() + "," + user.getName() + "," + user.getEmail() + "," + title + "," + fileSize;
    }


    public Attachment deserializeFromCsv(String[] csvStringSplit) {

        return new Attachment(Integer.valueOf(csvStringSplit[0]),
                new User(Integer.valueOf(csvStringSplit[1]), csvStringSplit[2], csvStringSplit[3]), csvStringSplit[4], Double.valueOf(csvStringSplit[5]));

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
