package com.finalproject.controller.login;

import com.finalproject.MainController;
import com.finalproject.factory.Factory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField pwdPassword;

    @FXML
    private void onClickLogin() {
        MainController mainController = (MainController) Factory.getInstance("MainController");

        try {
            // Depending on the user type, it loads either the AdminIndex or the EmployeeIndex
            mainController.changePrimaryStageScene("AdminRoot");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onClickForgotPassword() {
        MainController mainController = (MainController) Factory.getInstance("MainController");

        try {
            mainController.showForgotPassword();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
