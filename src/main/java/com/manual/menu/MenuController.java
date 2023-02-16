package com.manual.menu;

import com.manual.productManagement.AddProductController;
import com.manual.productManagement.ProductListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    @FXML
    public void addItem() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/add_employee.fxml"));
        final AddProductController addProductController = new AddProductController();


        try {
            Stage stage = new Stage();
            Parent parent = loader.load();
            loader.setRoot(parent);
            loader.setController(addProductController);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add a car");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are you sure you want to continue?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Exiting Inventory System");
        alert.setHeaderText("Exiting Inventory System");
        alert.showAndWait();

        if(alert.getResult() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    public void btnViewItems() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/list_products.fxml"));
        final ProductListController productListController = new ProductListController();
        try {
            Stage stage = new Stage();
            loader.setController(productListController);

            Parent parent = loader.load();

            loader.setRoot(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("View list of products");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
