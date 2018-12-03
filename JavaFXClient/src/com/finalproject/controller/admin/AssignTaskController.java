package com.finalproject.controller.admin;

import com.finalproject.factory.Factory;
import com.finalproject.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AssignTaskController {

    public static User employee;

    @FXML private Label lblEmployeeName;
    @FXML private TextField txtTaskTitle;
    @FXML private TextArea txtAreaTaskDescription;

    @FXML
    private void onClickAssign(ActionEvent event) {

    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        EmployeesTabController employeesTabController =
                (EmployeesTabController) Factory.getInstance("EmployeesTabController");
        employeesTabController.closeAssignTask();
    }

    @FXML
    private void initialize() {
        lblEmployeeName.setText(employee.getName() + " " + employee.getLastName());
    }
}
