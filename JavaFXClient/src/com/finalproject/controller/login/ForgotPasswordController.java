package com.finalproject.controller.login;

import com.finalproject.MainController;
import com.finalproject.factory.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ForgotPasswordController {

    @FXML private Label lblSecurityQuestion;
    @FXML private TextField txtSecurityAnswer;

    @FXML
    private void onClickCancel() {
        MainController mainController = (MainController) Factory.getInstance("MainController");

        mainController.closeForgotPassword();
    }

    @FXML
    private void onClickConfirm() {

    }
}
