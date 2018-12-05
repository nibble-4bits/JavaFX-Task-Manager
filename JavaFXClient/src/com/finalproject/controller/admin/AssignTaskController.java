package com.finalproject.controller.admin;

import com.finalproject.api.admin.AdminAPI;
import com.finalproject.factory.Factory;
import com.finalproject.model.Task;
import com.finalproject.model.User;
import com.finalproject.util.GUIUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        if (txtTaskTitle.getText().trim().isEmpty() || txtAreaTaskDescription.getText().trim().isEmpty()) {
            GUIUtils.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Fill out all fields",
                    "You must fill out all the fields before proceeding.");
            return;
        }

        Task task = new Task();
        task.setTitle(txtTaskTitle.getText());
        task.setDescription(txtAreaTaskDescription.getText());
        AdminAPI.assignTask(employee.getId(), task);
        GUIUtils.showAlert(Alert.AlertType.INFORMATION,
                "Success!",
                "Task successfully sent",
                "The task has been sent to this employee!");
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
