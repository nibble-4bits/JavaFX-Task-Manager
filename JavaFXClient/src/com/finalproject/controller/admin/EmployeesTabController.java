package com.finalproject.controller.admin;

import com.finalproject.MainController;
import com.finalproject.model.User;
import com.finalproject.util.GUIUtils;
import com.finalproject.util.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeesTabController {

    private static Stage primaryStage;
    private static Stage stageEmployeeDetails;
    private static Stage stageAssignTask;
    private static Stage performanceChart;
    private FilteredList<User> fl;

    @FXML private TableView tblEmployees;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colDepartment;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colLastName;
    @FXML private TableColumn<User, String> colEmail;
    @FXML private TableColumn<User, String> colDOB;
    @FXML private TableColumn<User, String> colHireDate;
    @FXML private TableColumn<User, String> colPassword;
    @FXML private TableColumn<User, String> colSecurityQuestion;
    @FXML private TableColumn<User, String> colSecurityAnswer;
    @FXML private ToolBar toolBarFilter;
    @FXML private CheckBox chkFilter;
    @FXML private TextField txtFilterTerm;
    @FXML private ChoiceBox choiceFilterCriteria;

    @FXML
    private void onClickAdd(ActionEvent event) {
        try {
            EmployeeDetailsController.initiator = 0;
            showEmployeeDetails(((Button)event.getSource()).getText());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onClickModify(ActionEvent event) {
        try {
            if (tblEmployees.getSelectionModel().getSelectedItem() != null) {
                EmployeeDetailsController.initiator = 1;
                EmployeeDetailsController.employee = (User) tblEmployees.getSelectionModel().getSelectedItem();
                showEmployeeDetails(((Button)event.getSource()).getText());
            }
            else {
                GUIUtils.showAlert(Alert.AlertType.WARNING,
                        "Warning",
                        "Select an employee",
                        "You must select an employee in order to modify their data");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onClickAssignTask(ActionEvent event) {
        try {
            if (tblEmployees.getSelectionModel().getSelectedItem() != null) {
                AssignTaskController.employee = (User) tblEmployees.getSelectionModel().getSelectedItem();
                showAssignTask();
            }
            else {
                GUIUtils.showAlert(Alert.AlertType.WARNING,
                        "Warning",
                        "Select an employee",
                        "You must select an employee in order to assign them a task");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onClickSeePerformance(ActionEvent event) {
        try {
            if (tblEmployees.getSelectionModel().getSelectedItem() != null) {
                PerformanceChartController.employee = (User) tblEmployees.getSelectionModel().getSelectedItem();
                showPerformanceChart();
            }
            else {
                GUIUtils.showAlert(Alert.AlertType.WARNING,
                        "Warning",
                        "Select an employee",
                        "You must select an employee in order to check their performance");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onCheckFilter(ActionEvent event) {
        if (chkFilter.isSelected()) {
            toolBarFilter.setVisible(true);
        }
        else {
            toolBarFilter.setVisible(false);
            txtFilterTerm.setText("");
        }
    }

    @FXML
    private void initialize() {
        this.primaryStage = MainController.getPrimaryStage();

        initializeChoiceBox();
        initializeTableColumns();
        addFilterEvent();

        fl = new FilteredList<>(loadPersons());

        tblEmployees.setItems(fl);
        GUIUtils.autoResizeColumns(tblEmployees);
    }

    private void initializeTableColumns() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colDepartment.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colDOB.setCellValueFactory(cellData -> cellData.getValue().dateOfBirthProperty().asString());
        colHireDate.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty().asString());
        colPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        colSecurityQuestion.setCellValueFactory(cellData -> cellData.getValue().securityQuestionProperty());
        colSecurityAnswer.setCellValueFactory(cellData -> cellData.getValue().securityAnswerProperty());
    }

    private void initializeChoiceBox() {
        choiceFilterCriteria.getItems().add("Name");
        choiceFilterCriteria.getItems().add("Email");
        choiceFilterCriteria.setValue("Name");
    }

    private void addFilterEvent() {
        txtFilterTerm.textProperty().addListener((obs, oldText, newText) -> {
            fl.setPredicate(user -> {
                if (newText == null || newText.isEmpty()) {
                    return true;
                }

                if (choiceFilterCriteria.getValue().toString().equals("Name")) {
                    return user.getName().contains(newText);
                }
                else if (choiceFilterCriteria.getValue().toString().equals("Email")) {
                    return user.getEmail().contains(newText);
                }

                return false;
            });
        });
    }

    private ObservableList<User> loadPersons() {
        ArrayList<User> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setId(i);
            user.setDepartment("Department " + i);
            user.setEmail("thisIsEmail" + i + "@gmail.com");
            user.setDateOfBirth(LocalDate.of(1998, 4, 3));
            user.setHireDate(LocalDate.of(1998, 4, 3));
            user.setLastName("Fischer " + i);
            user.setName("Robert James " + i);
            user.setPassword("thisIsMyPassword" + i);
            user.setSecurityAnswer("Security answers " + i);
            user.setSecurityQuestion("Security question " + i);
            list.add(user);
        }

        return FXCollections.observableArrayList(list);
    }

    private void showEmployeeDetails(String initiator) throws Exception {
        stageEmployeeDetails = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("EmployeeDetails")));
        stageEmployeeDetails.initOwner(primaryStage);
        stageEmployeeDetails.initModality(Modality.APPLICATION_MODAL);
        stageEmployeeDetails.setResizable(false);

        if (initiator.equals("Add...")) {
            stageEmployeeDetails.setTitle(SceneLoader.getTitle("EmployeeDetailsAdd"));
        }
        else if (initiator.equals("Modify...")) {
            stageEmployeeDetails.setTitle(SceneLoader.getTitle("EmployeeDetailsModify"));
        }

        stageEmployeeDetails.setScene(new Scene(root));
        stageEmployeeDetails.show();
    }

    public void closeEmployeeDetails() {
        if (stageEmployeeDetails != null) {
            stageEmployeeDetails.close();
        }
    }

    private void showAssignTask() throws Exception {
        stageAssignTask = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("AssignTask")));
        stageAssignTask.initOwner(primaryStage);
        stageAssignTask.initModality(Modality.APPLICATION_MODAL);
        stageAssignTask.setResizable(false);
        stageAssignTask.setTitle(SceneLoader.getTitle("AssignTask"));
        stageAssignTask.setScene(new Scene(root));
        stageAssignTask.show();
    }

    public void closeAssignTask() {
        if (stageAssignTask != null) {
            stageAssignTask.close();
        }
    }

    private void showPerformanceChart() throws Exception {
        performanceChart = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("PerformanceChart")));
        performanceChart.initOwner(primaryStage);
        performanceChart.initModality(Modality.APPLICATION_MODAL);
        performanceChart.setResizable(false);
        performanceChart.setTitle(SceneLoader.getTitle("PerformanceChart"));
        performanceChart.setScene(new Scene(root));
        performanceChart.show();
    }
}
