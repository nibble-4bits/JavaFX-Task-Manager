package com.finalproject.controller.employee;

import com.finalproject.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AssignedTasksController {

    @FXML private TableView tblTasks;
    @FXML private TableColumn<Task, String> colTitle;
    @FXML private TableColumn<Task, String> colStatus;
    @FXML private ChoiceBox choiceFilterCriteria;
    @FXML private Label lblTitle;
    @FXML private Label lblStatus;
    @FXML private TextArea txtAreaDescription;

    @FXML
    private void onClickContinue(ActionEvent event) {

    }

    @FXML
    private void onClickLogout(ActionEvent event) {

    }

    @FXML
    private void initialize() {
        choiceFilterCriteria.getItems().add("None");
        choiceFilterCriteria.getItems().add("Pending");
        choiceFilterCriteria.getItems().add("Work in progress");
        choiceFilterCriteria.getItems().add("Finished");
        choiceFilterCriteria.setValue("None");
    }
}
