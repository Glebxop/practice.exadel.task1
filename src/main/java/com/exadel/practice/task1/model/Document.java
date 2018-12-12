package com.exadel.practice.task1.model;

import com.exadel.practice.task1.actions.Addable;

import java.util.List;

public class Document implements Addable {


    private int id;
    private String title;
    private String text;
    private List<AbstractUserContent> userContentList;

    public Document(int id, String title, String text, List<AbstractUserContent> userContentList) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.userContentList = userContentList;
    }

   public void add(AbstractUserContent userContent){
       userContentList.add(userContent);


    }

    public List<AbstractUserContent> getUserContentList() {
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

    public void setUserContentList(List<AbstractUserContent> userContentList) {
        this.userContentList = userContentList;
    }
}
