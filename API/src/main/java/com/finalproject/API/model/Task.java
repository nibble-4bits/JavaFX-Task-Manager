package com.finalproject.API.model;

public class Task {

    public static final int PENDING = 0;
    public static final int WIP = 1; // work in progress
    public static final int FINISHED = 2;

    private int Id;
    private User User;
    private String Title;
    private String Description;
    private int Status;

    public Task() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public com.finalproject.API.model.User getUser() {
        return User;
    }

    public void setUser(com.finalproject.API.model.User user) {
        User = user;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
