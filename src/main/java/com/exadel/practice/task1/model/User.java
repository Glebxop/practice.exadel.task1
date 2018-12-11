package com.exadel.practice.task1.model;

import com.exadel.practice.task1.actions.Showable;

public class User implements Showable {



    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void show() {
        System.out.println( this);
    }
}