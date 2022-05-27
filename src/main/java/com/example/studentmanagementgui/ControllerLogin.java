package com.example.studentmanagementgui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class ControllerLogin {
    @FXML
    private Label welcomeText;
    @FXML
    private Button Login;
    @FXML
    private TextField WebID;
    @FXML
    private TextField password;

    public void login(ActionEvent event) throws IOException {
        String webID = WebID.getText();
        String pass = password.getText();
        if (webID.equalsIgnoreCase("registrar") && pass.equalsIgnoreCase("registrar")){
            SceneController s = new SceneController();
            s.switchToDatabaseScene(event);
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setContentText("Please login using the webID 'registrar' and password 'registrar'");
            alert.setHeaderText("Login error");
            alert.showAndWait();
        }
    }

}