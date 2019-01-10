package com.exadel.practice.usercontent.model;


import java.util.List;

public class Document  {



    private int id;
    private String title;
    private String text;
    private List<Attachment> userContentList;

    public Document() {
    }

    public Document(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Document(int id, String title, String text, List<Attachment> userContentList) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.userContentList = userContentList;
    }

   public void add(Attachment attachment){
       userContentList.add(attachment);


    }

    public List<Attachment> getUserContentList() {
        return userContentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserContentList(List<Attachment> userContentList) {
        this.userContentList = userContentList;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", userContentList=" + userContentList +
                '}';
    }

}
