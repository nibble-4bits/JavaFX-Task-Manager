package com.finalproject.controller.login;

import com.finalproject.MainController;
import com.finalproject.api.login.LoginAPI;
import com.finalproject.factory.Factory;
import com.finalproject.model.User;
import com.finalproject.util.GUIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ForgotPasswordController {

    public static String email;
    private User security;

    @FXML private Label lblSecurityQuestion;
    @FXML private TextField txtSecurityAnswer;

    @FXML
    private void onClickCancel() {
        MainController mainController = (MainController) Factory.getInstance("MainController");

        mainController.closeForgotPassword();
    }

    @FXML
    private void onClickConfirm() {
        if (txtSecurityAnswer.getText().trim().equals(security.getSecurityAnswer())) {
            LoginAPI.sendPasswordRecoveryEmail(email);
            GUIUtils.showAlert(Alert.AlertType.INFORMATION,
                    "Success!",
                    "Correct answer",
                    "An email containing your password has been sent " +
                            "to the email address you have provided.");
        }
        else {
            GUIUtils.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Incorrect answer",
                    "The answer you provided is not correct. Try again.");
        }
    }

    @FXML
    private void initialize() {
        User user = new User();
        user.setEmail(email);

        security = LoginAPI.getSecurityQuestionAndAnswer(user);

        if (security.getSecurityAnswer() != null || security.getSecurityAnswer() != null) {
            lblSecurityQuestion.setText(security.getSecurityQuestion());
        }
        else {
            MainController mainController = (MainController) Factory.getInstance("MainController");
            GUIUtils.showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Nonexistent user",
                    "There is no user with the email you have provided. Check again.");
        }
    }
}
