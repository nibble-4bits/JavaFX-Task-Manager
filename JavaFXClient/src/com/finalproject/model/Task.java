package com.finalproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    public static final int PENDING = 0;
    public static final int WIP = 1; // work in progress
    public static final int FINISHED = 2;

    private SimpleIntegerProperty id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleIntegerProperty status;

    public Task() {
        id = new SimpleIntegerProperty();
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        status = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getStatus() {
        return status.get();
    }

    public SimpleIntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }
}
