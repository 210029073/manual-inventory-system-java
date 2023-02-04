package com.manual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ManualRunComponent extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com.manual.main/login.fxml"));
        HBox container = fxmlLoader.load();
        stage.setScene(new Scene(container));
        stage.setResizable(false);
        stage.setTitle("Inventory Management System");
        stage.show();
    }
}