package com.manual.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    /**
     * This will simply show the dashboard gui.
     * @author Ibrahim Ahmad
     * @since 10/02/2023*/
    @FXML
    public void btnViewDash() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/dashboard.fxml"));

        try {
            Stage stage = new Stage();
            Parent parent = loader.load();

            loader.setRoot(parent);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Dashboard");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This will simply exit the menu
     * @author Ibrahim Ahmad (210029073)
     * @since 10/02/2023*/
    @FXML
    public void exitMenu() {
        System.exit(0);
    }
}
