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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {


    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
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
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        MainController.setPrimaryStage(primaryStage);

        primaryStage.setOnCloseRequest(e -> {
            LoginAPI.deauthenticate();
            Platform.exit();
            System.exit(0);
        });

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
