package com.finalproject.controller.admin;

import com.finalproject.api.admin.AdminAPI;
import com.finalproject.factory.Factory;
import com.finalproject.model.Department;
import com.finalproject.model.User;
import com.finalproject.util.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeDetailsController {

    public static int initiator;
    public static User employee;

    @FXML private ComboBox cmbDepartment;
    @FXML private TextField txtName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtEmail;
    @FXML private DatePicker dateBirth;
    @FXML private DatePicker dateHire;
    @FXML private PasswordField passField;
    @FXML private TextField txtSecurityQuestion;
    @FXML private TextField txtSecurityAnswer;
    @FXML private Button btnAddOrUpdate;

    @FXML
    private void onClickAddOrUpdate(ActionEvent event) {
        if (!isFormValid()) {
            GUIUtils.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Fill out all fields",
                    "You must fill out all the fields before proceeding.");
            return;
        }

        if (initiator == 0) { // add
            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPassword(passField.getText());
            user.setSecurityQuestion(txtSecurityQuestion.getText());
            user.setSecurityAnswer(txtSecurityAnswer.getText());
            user.setName(txtName.getText());
            user.setLastName(txtLastName.getText());
            user.setHireDate(dateHire.getValue());
            user.setDateOfBirth(dateBirth.getValue());

            AdminAPI.addUser(cmbDepartment.getSelectionModel().getSelectedIndex() + 1, user);
            GUIUtils.showAlert(Alert.AlertType.INFORMATION,
                    "Success!",
                    "Employee saved",
                    "The employee has been saved!");
        }
        else if (initiator == 1) { //modify
            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPassword(passField.getText());
            user.setSecurityQuestion(txtSecurityQuestion.getText());
            user.setSecurityAnswer(txtSecurityAnswer.getText());
            user.setName(txtName.getText());
            user.setLastName(txtLastName.getText());
            user.setHireDate(dateHire.getValue());
            user.setDateOfBirth(dateBirth.getValue());

            AdminAPI.updateUser(employee.getId(), cmbDepartment.getSelectionModel().getSelectedIndex() + 1, user);
            GUIUtils.showAlert(Alert.AlertType.INFORMATION,
                    "Success!",
                    "Employee modified",
                    "The employee has been modified!");
        }
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        EmployeesTabController employeesTabController =
                (EmployeesTabController) Factory.getInstance("EmployeesTabController");
        employeesTabController.closeEmployeeDetails();
    }

    @FXML
    private void initialize() {
        for (Department d : AdminAPI.getAllDepartments()) {
            cmbDepartment.getItems().add(d.getName());
        }

        dateBirth.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        dateHire.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        if (initiator == 0) { // add
            btnAddOrUpdate.setText("Add");
        }
        else if (initiator == 1) { //modify
            cmbDepartment.getSelectionModel().select(employee.getDepartment());
            txtName.setText(employee.getName());
            txtLastName.setText(employee.getLastName());
            txtEmail.setText(employee.getEmail());
            dateBirth.setValue(employee.getDateOfBirth());
            dateHire.setValue(employee.getHireDate());
            passField.setText(employee.getPassword());
            txtSecurityQuestion.setText(employee.getSecurityQuestion());
            txtSecurityAnswer.setText(employee.getSecurityAnswer());
            btnAddOrUpdate.setText("Update");
        }
    }

    private boolean isFormValid() {
        /*if (cmbDepartment.promptTextProperty().get().equals("Please select an option")) {
            return false;
        }*/
        if (txtName.getText().trim().isEmpty()) {
            return false;
        }
        if (txtLastName.getText().trim().isEmpty()) {
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            return false;
        }
        if (dateBirth.getValue().toString().trim().isEmpty()) {
            return false;
        }
        if (dateHire.getValue().toString().trim().isEmpty()) {
            return false;
        }
        if (passField.getText().trim().isEmpty()) {
            return false;
        }
        if (txtSecurityQuestion.getText().trim().isEmpty()) {
            return false;
        }
        if (txtSecurityAnswer.getText().trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
