package com.exadel.practice.usercontent.model;


import java.util.List;

public class UserComment {
    private User user;
    private List<Comment> comment;

    public UserComment(User user, List<Comment> comment) {
        this.user = user;
        this.comment = comment;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "user=" + user +
                ", comment=" + comment +
                '}';
    }
}
