package com.example.studentmanagementgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class SceneController {
    private Stage stage;
    private Scene scene;

    public void switchToLoginScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-scene.fxml"));
        stage = (Stage)(((Node)(event.getSource())).getScene().getWindow());
        scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDatabaseScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("database-scene.fxml"));
        stage = (Stage)(((Node)(event.getSource())).getScene().getWindow());
        scene = new Scene(fxmlLoader.load(), 900, 500);
        ControllerDatabase controllerDatabase = fxmlLoader.getController();
        controllerDatabase.updateTable();
        stage.setScene(scene);
        stage.show();

    }
}
