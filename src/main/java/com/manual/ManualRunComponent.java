package com.manual;

import com.manual.menu.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        fxmlLoader.setLocation(getClass().getResource("/com.manual.main/menu.fxml"));
        fxmlLoader.setController(new MenuController());
        VBox container = fxmlLoader.load();
        stage.setScene(new Scene(container));
        stage.setResizable(true);
        stage.setTitle("Inventory Management System - Dashboard");
        stage.getIcons().add(new Image(getClass().getResource("/com.manual.main/Manual.jpg").toString()));
        stage.show();
    }
}