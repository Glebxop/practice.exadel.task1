package com.exadel.practice.task1.model;


import com.exadel.practice.task1.actions.Downloadeble;

public class Attachment extends AbstractUserContent implements Downloadeble, Comparable<Attachment> {



    private double fileSize;
    final static int IDUSERCONTANT=0;


    public Attachment(int id, User user, String title, double fileSize) {
        super(id, user, title);
        this.fileSize = fileSize;
    }

    public void download(){
        System.out.println("Downloading "+this);
    }
    public String serializeToCsv(){
        return IDUSERCONTANT+","+id+","+user.getId()+","+user.getName()+","+user.getEmail()+","+title+","+fileSize;
    }


   /* public Object deserializeFromCsv(String csvString) {
        String[]csvStringSplit=csvString.split(",");

        return new Annotation(Integer.valueOf(csvStringSplit[0]),
                new User(Integer.valueOf(csvStringSplit[1]),csvStringSplit[2],csvStringSplit[3]),csvStringSplit[4],csvStringSplit[5]);

    }*/

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
