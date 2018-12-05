package com.finalproject.controller.employee;

import com.finalproject.MainController;
import com.finalproject.api.employee.EmployeeAPI;
import com.finalproject.api.login.LoginAPI;
import com.finalproject.factory.Factory;
import com.finalproject.model.Task;
import com.finalproject.util.GUIUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AssignedTasksController {

    private List<Task> assignedTasks;
    private FilteredList<Task> filteredList;
    private Timer timer;
    private TimerTask updateTasksEveryHalfSecond = new TimerTask() {
        @Override
        public void run() {
            List<Task> tmp = EmployeeAPI.getAllTasks();
            if (!areListsEqual(tmp, assignedTasks)) {
                filteredList = new FilteredList<>(FXCollections.observableList(tmp));
                tblTasks.setItems(filteredList);
                assignedTasks = tmp;
            }
        }
    };

    @FXML
    private TableView tblTasks;
    @FXML
    private TableColumn<Task, Integer> colId;
    @FXML
    private TableColumn<Task, String> colTitle;
    @FXML
    private TableColumn<Task, Integer> colStatus;
    @FXML
    private ChoiceBox choiceFilterCriteria;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblStatus;
    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private void onClickContinue(ActionEvent event) {
        Task selectedTask = (Task) tblTasks.getSelectionModel().getSelectedItem();
        if (selectedTask == null) {
            GUIUtils.showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Select a task",
                    "You must select a task in order to move their status forward");
        }

        if (selectedTask.getStatus() == Task.FINISHED) {
            GUIUtils.showAlert(Alert.AlertType.WARNING, "Warning", "The task is finished", "The selected task is already finished, you can't advance it anymore");
            return;
        }

        EmployeeAPI.advanceTask(selectedTask.getId());
        GUIUtils.showAlert(Alert.AlertType.INFORMATION, "Success!", "Task has been advanced", "Your task has been advanced to the next stage");
    }

    @FXML
    private void onClickLogout(ActionEvent event) {
        MainController mainController = (MainController) Factory.getInstance("MainController");
        LoginAPI.deauthenticate();
        try {
            mainController.changePrimaryStageScene("Login");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        timer.cancel();
    }

    @FXML
    private void initialize() {
        initializeChoiceBox();
        initializeTableColumns();
        addFilterEvent();
        addTableSelectEvent();

        filteredList = new FilteredList<>(loadTasks());

        tblTasks.setItems(filteredList);

        timer = new Timer();
        timer.scheduleAtFixedRate(updateTasksEveryHalfSecond, 1000, 500);
    }

    private void initializeChoiceBox() {
        choiceFilterCriteria.getItems().add("None");
        choiceFilterCriteria.getItems().add("Pending");
        choiceFilterCriteria.getItems().add("Work in progress");
        choiceFilterCriteria.getItems().add("Finished");
        choiceFilterCriteria.setValue("None");
    }

    private void initializeTableColumns() {
        colTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty().asObject());
    }

    private ObservableList<Task> loadTasks() {
        assignedTasks = EmployeeAPI.getAllTasks();
        return FXCollections.observableArrayList(assignedTasks);
    }

    private void addFilterEvent() {
        choiceFilterCriteria.valueProperty().addListener((obs, oldText, newText) -> {
            filteredList.setPredicate(task -> {
                if (choiceFilterCriteria.getValue().toString().equals("None")) {
                    return true;
                } else if (choiceFilterCriteria.getValue().toString().equals("Pending")) {
                    return task.getStatus() == Task.PENDING;
                } else if (choiceFilterCriteria.getValue().toString().equals("Work in progress")) {
                    return task.getStatus() == Task.WIP;
                } else if (choiceFilterCriteria.getValue().toString().equals("Finished")) {
                    return task.getStatus() == Task.FINISHED;
                }

                return false;
            });
        });
    }

    private void addTableSelectEvent() {
        tblTasks.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        lblTitle.setText(((Task) newSelection).getTitle());
                        txtAreaDescription.setText(((Task) newSelection).getDescription());

                        if (((Task) newSelection).getStatus() == Task.PENDING) {
                            lblStatus.setText("PENDING");
                        } else if (((Task) newSelection).getStatus() == Task.WIP) {
                            lblStatus.setText("WIP");
                        } else if (((Task) newSelection).getStatus() == Task.FINISHED) {
                            lblStatus.setText("FINISHED");
                        }
                    }
                }
        );
    }

    public boolean areListsEqual(List<Task> list1, List<Task> list2) {
        //null checking
        if (list1 == null && list2 == null)  return true;

        if ((list1 == null && list2 != null) || (list1 != null && list2 == null))  return false;

        if (list1.size() != list2.size())  return false;

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getStatus() != list2.get(i).getStatus()) return false;
        }

        return true;
    }
}
