package com.finalproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    public static final int PENDING = 0;
    public static final int WIP = 1; // work in progress
    public static final int FINISHED = 2;

    private SimpleIntegerProperty Id;
    private SimpleObjectProperty<User> User;
    private SimpleStringProperty Title;
    private SimpleStringProperty Description;
    private SimpleIntegerProperty Status;

    public Task() {
    }

    public int getId() {
        return Id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public User getUser() {
        return User.get();
    }

    public SimpleObjectProperty<User> userProperty() {
        return User;
    }

    public void setUser(com.finalproject.model.User user) {
        this.User.set(user);
    }

    public String getTitle() {
        return Title.get();
    }

    public SimpleStringProperty titleProperty() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title.set(title);
    }

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public int getStatus() {
        return Status.get();
    }

    public SimpleIntegerProperty statusProperty() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status.set(status);
    }
}
