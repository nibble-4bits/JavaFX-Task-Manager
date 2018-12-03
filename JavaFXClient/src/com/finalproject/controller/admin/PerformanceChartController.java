package com.finalproject.controller.admin;

import com.finalproject.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class PerformanceChartController {

    public static User employee;

    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis xAxis;

    @FXML
    private void initialize() {
        ObservableList<String> taskStatuses = FXCollections.observableArrayList("Pending", "WIP", "Finished");

        barChart.setTitle(employee.getName() + " " + employee.getLastName() + "'s performance");
        xAxis.setCategories(taskStatuses);

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        /*for (int i = 0; i < months.length - 1; i++) {
            series.getData().add(new XYChart.Data<>(months[i], birthdayCount[i]));
        }*/
        barChart.getData().add(series);
    }
}
