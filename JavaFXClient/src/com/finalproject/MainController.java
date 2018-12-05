package com.finalproject;

import com.finalproject.controller.login.ForgotPasswordController;
import com.finalproject.controller.login.LoginController;
import com.finalproject.util.SceneLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    private static Stage primaryStage;
    private static Stage stageForgotPass;
    private static Stage stageTokenInput;
    public static boolean loggedIn = false;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainController.primaryStage = primaryStage;
    }

    public void showForgotPassword() throws Exception {
        stageForgotPass = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("ForgotPassword")));

        stageForgotPass.setTitle(SceneLoader.getTitle("ForgotPassword"));
        stageForgotPass.initOwner(primaryStage);
        stageForgotPass.initModality(Modality.APPLICATION_MODAL);
        stageForgotPass.setResizable(false);
        stageForgotPass.setScene(new Scene(root));
        stageForgotPass.showAndWait();
    }

    public void closeForgotPassword() {
        if (stageForgotPass != null) {
            stageForgotPass.close();
        }
    }

    public void showTokenInput() throws Exception {
        stageTokenInput = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene("TokenInput")));

        stageTokenInput.setTitle(SceneLoader.getTitle("TokenInput"));
        stageTokenInput.initOwner(primaryStage);
        stageTokenInput.initModality(Modality.APPLICATION_MODAL);
        stageTokenInput.setResizable(false);
        stageTokenInput.setScene(new Scene(root));
        stageTokenInput.show();
    }

    public void closeTokenInput() {
        if (stageTokenInput != null) {
            stageTokenInput.close();
        }
    }

    public void changePrimaryStageScene(String sceneName) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(SceneLoader.getScene(sceneName)));
        Scene scene = new Scene(root);
        primaryStage.setTitle(SceneLoader.getTitle(sceneName));
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
