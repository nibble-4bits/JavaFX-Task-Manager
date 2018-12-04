package com.finalproject.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class User {
    public static final int ADMIN = 0;
    public static final int EMPLOYEE = 1;

    private SimpleIntegerProperty id;
    private SimpleStringProperty department;
    private SimpleStringProperty email;
    private SimpleStringProperty name;
    private SimpleStringProperty lastName;
    private SimpleObjectProperty<LocalDate> dateOfBirth;
    private SimpleObjectProperty<LocalDate> hireDate;
    private SimpleStringProperty password;
    private SimpleStringProperty securityQuestion;
    private SimpleStringProperty securityAnswer;
    private SimpleIntegerProperty type;

    public User() {
        this.id = new SimpleIntegerProperty();
        this.department = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.dateOfBirth = new SimpleObjectProperty<>();
        this.hireDate = new SimpleObjectProperty<>();
        this.password = new SimpleStringProperty();
        this.securityQuestion = new SimpleStringProperty();
        this.securityAnswer = new SimpleStringProperty();
        this.type = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth.get();
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public LocalDate getHireDate() {
        return hireDate.get();
    }

    public ObjectProperty<LocalDate> hireDateProperty() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate.set(hireDate);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getSecurityQuestion() {
        return securityQuestion.get();
    }

    public StringProperty securityQuestionProperty() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion.set(securityQuestion);
    }

    public String getSecurityAnswer() {
        return securityAnswer.get();
    }

    public StringProperty securityAnswerProperty() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer.set(securityAnswer);
    }

    public int getType() {
        return type.get();
    }

    public IntegerProperty typeProperty() {
        return type;
    }

    public void setType(int type) {
        this.type.set(type);
    }
}
