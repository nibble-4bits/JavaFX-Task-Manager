package com.finalproject.controller.admin;

import com.finalproject.model.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DepartmentsTabController {

    @FXML private TableView tblDepartments;
    @FXML private TableColumn<Department, Integer> colId;
    @FXML private TableColumn<Department, String> colDepartment;
    @FXML private TextField txtDepartmentName;

    @FXML
    private void onClickAddDepartment(ActionEvent event) {

    }

    @FXML
    private void initialize() {

    }
}
