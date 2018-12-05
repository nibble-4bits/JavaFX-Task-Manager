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
        if (initiator == 0) { // add
            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPassword(passField.getText());
            user.setSecurityQuestion(txtSecurityQuestion.getText());
            user.setSecurityAnswer(txtSecurityAnswer.getText());
            user.setName(txtName.getText());
            user.setLastName(txtLastName.getText());
            user.setHireDate(dateBirth.getValue());
            user.setDateOfBirth(dateHire.getValue());

            AdminAPI.addUser(cmbDepartment.getSelectionModel().getSelectedIndex() + 1, user);
            GUIUtils.showAlert(Alert.AlertType.INFORMATION, "Success!", "Employee saved", "The employee has been saved!");
        }
        else if (initiator == 1) { //modify
            User user = new User();
            user.setEmail(txtEmail.getText());
            user.setPassword(passField.getText());
            user.setSecurityQuestion(txtSecurityQuestion.getText());
            user.setSecurityAnswer(txtSecurityAnswer.getText());
            user.setName(txtName.getText());
            user.setLastName(txtLastName.getText());
            user.setHireDate(dateBirth.getValue());
            user.setDateOfBirth(dateHire.getValue());

            AdminAPI.updateUser(employee.getId(), cmbDepartment.getSelectionModel().getSelectedIndex() + 1, user);
            GUIUtils.showAlert(Alert.AlertType.INFORMATION, "Success!", "Employee modified", "The employee has been modified!");
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

    private ObservableList<User> loadEmployees() {
        List<User> list = AdminAPI.getAllEmployees();
        return FXCollections.observableArrayList(list);
    }
}
