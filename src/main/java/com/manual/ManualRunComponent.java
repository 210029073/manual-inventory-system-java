package com.manual;

import com.manual.dashboard.DashboardController;
import com.manual.login.LoginController;
import com.manual.menu.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManualRunComponent extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com.manual.main/dashboard.fxml"));
        HBox container = fxmlLoader.load();
        stage.setScene(new Scene(container));
        stage.setResizable(false);
        stage.setTitle("Inventory Management System - Dashboard");
        stage.show();
    }
}