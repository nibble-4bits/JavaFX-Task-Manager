package com.finalproject.controller.admin;

import com.finalproject.factory.Factory;
import com.finalproject.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        if (initiator == 0) { // add
            cmbDepartment.getValue();
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
        if (initiator == 0) { // add
            //cmbDepartment.setItems(); // load items from database
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
}
