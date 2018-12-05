package com.finalproject.controller.login;

import com.finalproject.MainController;
import com.finalproject.api.login.LoginAPI;
import com.finalproject.factory.Factory;
import com.finalproject.util.GUIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class TokenInputController {

    @FXML
    private TextField txtToken;

    @FXML
    private void onClickConfirm() {
        String response = LoginAPI.checkIfTokenValid(txtToken.getText());

        if (!response.equals("INVALID")) {
            GUIUtils.showAlert(Alert.AlertType.INFORMATION, "Success", "Login enabled", "You are now able to log in");
            MainController mainController = (MainController) Factory.getInstance("MainController");
            mainController.closeTokenInput();
        }
        else {
            GUIUtils.showAlert(Alert.AlertType.WARNING, "Warning", "Token expired", "This token has expired. You must try to log in again to request a new token");
        }
    }
}
