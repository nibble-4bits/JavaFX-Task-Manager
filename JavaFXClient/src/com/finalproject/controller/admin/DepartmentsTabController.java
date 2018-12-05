package com.finalproject.controller.admin;

import com.finalproject.api.admin.AdminAPI;
import com.finalproject.model.Department;
import com.finalproject.util.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class DepartmentsTabController {

    @FXML private TableView tblDepartments;
    @FXML private TableColumn<Department, Integer> colId;
    @FXML private TableColumn<Department, String> colDepartment;
    @FXML private TextField txtDepartmentName;

    @FXML
    private void onClickAddDepartment(ActionEvent event) {
        if (txtDepartmentName.getText().trim().isEmpty()) {
            GUIUtils.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Fill out all fields",
                    "Fill out the department name field before proceeding.");
            return;
        }

        Department department = new Department();
        department.setName(txtDepartmentName.getText());
        AdminAPI.addDepartment(department);

        GUIUtils.showAlert(Alert.AlertType.INFORMATION,
                "Success!",
                "Department saved",
                "The new department has been saved!");
        tblDepartments.setItems(loadDepartments());
    }

    @FXML
    private void initialize() {
        initializeTableColumns();
        tblDepartments.setItems(loadDepartments());
    }

    private void initializeTableColumns() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colDepartment.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    private ObservableList<Department> loadDepartments() {
        List<Department> list = AdminAPI.getAllDepartments();
        return FXCollections.observableArrayList(list);
    }
}
