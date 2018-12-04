package com.finalproject.API.model;

import java.sql.Timestamp;

public class Token {

    private int Id;
    private User user;
    private Timestamp ExpirationDate;

    public Token() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        ExpirationDate = expirationDate;
    }
}
