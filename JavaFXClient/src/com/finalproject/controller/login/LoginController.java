package com.finalproject.controller.login;

import com.finalproject.MainController;
import com.finalproject.api.login.LoginAPI;
import com.finalproject.factory.Factory;
import com.finalproject.model.User;
import com.finalproject.util.GUIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField pwdPassword;

    @FXML
    private void onClickLogin() {
        MainController mainController = (MainController) Factory.getInstance("MainController");

        try {
            User tmp = new User();
            tmp.setEmail(txtEmail.getText());
            tmp.setPassword(pwdPassword.getText());

            User authUser = LoginAPI.authenticate(tmp);

            if (authUser.getEmail().equals("alreadylogged")) {
                GUIUtils.showAlert(Alert.AlertType.WARNING,
                        "Session active",
                        "Another user has already logged in",
                        "An email has been sent to the provided email address, you have 1 minute to enter it to be able to log in");

                try {
                    mainController.showTokenInput();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (authUser.getEmail().equals("nonexistent")) {
                GUIUtils.showAlert(Alert.AlertType.ERROR,
                        "An error has occurred",
                        "User not found",
                        "There is no user that uses the email you provided");
            }
            else if (authUser.getEmail().equals("passmismatch")) {
                GUIUtils.showAlert(Alert.AlertType.ERROR,
                        "An error has occurred",
                        "Password mismatch",
                        "The password you provided does not match the saved password");
            }
            else { // Depending on the user type, it loads either the AdminIndex or the EmployeeIndex
                MainController.loggedIn = true;
                if (authUser.getType() == User.ADMIN) {
                    mainController.changePrimaryStageScene("AdminRoot");
                }
                else if (authUser.getType() == User.EMPLOYEE) {
                    mainController.changePrimaryStageScene("AssignedTasks");
                }
            }
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
