package com.finalproject.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class User {
    public static final int ADMIN = 0;
    public static final int EMPLOYEE = 1;

    private SimpleIntegerProperty Id;
    private SimpleStringProperty Department;
    private SimpleStringProperty Email;
    private SimpleStringProperty Name;
    private SimpleStringProperty LastName;
    private SimpleObjectProperty<LocalDate> DateOfBirth;
    private SimpleObjectProperty<LocalDate> HireDate;
    private SimpleStringProperty Password;
    private SimpleStringProperty SecurityQuestion;
    private SimpleStringProperty SecurityAnswer;
    private SimpleIntegerProperty Type;

    public User() {
        this.Id = new SimpleIntegerProperty();
        this.Department = new SimpleStringProperty();
        this.Email = new SimpleStringProperty();
        this.Name = new SimpleStringProperty();
        this.LastName = new SimpleStringProperty();
        this.DateOfBirth = new SimpleObjectProperty<>();
        this.HireDate = new SimpleObjectProperty<>();
        this.Password = new SimpleStringProperty();
        this.SecurityQuestion = new SimpleStringProperty();
        this.SecurityAnswer = new SimpleStringProperty();
        this.Type = new SimpleIntegerProperty();
    }

    public int getId() {
        return Id.get();
    }

    public IntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public String getDepartment() {
        return Department.get();
    }

    public StringProperty departmentProperty() {
        return Department;
    }

    public void setDepartment(String department) {
        this.Department.set(department);
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email.set(email);
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public String getLastName() {
        return LastName.get();
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth.get();
    }

    public ObjectProperty<LocalDate> dateOfBirthProperty() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth.set(dateOfBirth);
    }

    public LocalDate getHireDate() {
        return HireDate.get();
    }

    public ObjectProperty<LocalDate> hireDateProperty() {
        return HireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.HireDate.set(hireDate);
    }

    public String getPassword() {
        return Password.get();
    }

    public StringProperty passwordProperty() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password.set(password);
    }

    public String getSecurityQuestion() {
        return SecurityQuestion.get();
    }

    public StringProperty securityQuestionProperty() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.SecurityQuestion.set(securityQuestion);
    }

    public String getSecurityAnswer() {
        return SecurityAnswer.get();
    }

    public StringProperty securityAnswerProperty() {
        return SecurityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.SecurityAnswer.set(securityAnswer);
    }

    public int getType() {
        return Type.get();
    }

    public IntegerProperty typeProperty() {
        return Type;
    }

    public void setType(int type) {
        this.Type.set(type);
    }
}
