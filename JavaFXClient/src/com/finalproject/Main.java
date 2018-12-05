package com.finalproject;

import com.finalproject.api.login.LoginAPI;
import com.finalproject.factory.Factory;
import com.finalproject.util.SceneLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    // Every second we poll the session state
    private Timeline checkSessionState = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

        // If the session becomes false and the user is logged in we logged them out
        if (!LoginAPI.checkSessionState() && MainController.loggedIn) {
            MainController mainController = (MainController) Factory.getInstance("MainController");
            try {
                mainController.changePrimaryStageScene("Login");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            MainController.loggedIn = false;
            LoginAPI.email = null;
        }
    }));

    @Override
    public void init() throws Exception {
        Factory.getInstance("MainController");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("Login")));
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("file:res/app_icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        MainController.setPrimaryStage(primaryStage);

        primaryStage.setOnCloseRequest(e -> {
            LoginAPI.deauthenticate();
            Platform.exit();
            System.exit(0);
        });

        checkSessionState.setCycleCount(Timeline.INDEFINITE);
        checkSessionState.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
