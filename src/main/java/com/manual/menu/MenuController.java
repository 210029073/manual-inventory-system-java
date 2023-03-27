package com.manual.menu;

import com.manual.AboutController;
import com.manual.dashboard.DashboardController;
import com.manual.orders.OrdersController;
import com.manual.productManagement.AddProductController;
import com.manual.productManagement.ProductListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private VBox container;

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
            final DashboardController dashboardController = new DashboardController();
            loader.setController(dashboardController);

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
        this.container.getScene().getWindow().hide();
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
            stage.setResizable(true);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnManageOrders(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/orders.fxml"));
        final OrdersController oc = new OrdersController();
        try {
            Stage stage = new Stage();
            loader.setController(oc);
            Parent parent = loader.load();

            loader.setRoot(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Manage Orders");
            stage.setResizable(true);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onAnnualSales() {
        DashboardController dc = new DashboardController();
        dc.onAnnualSales();
    }

    @FXML
    public void onTrendingCars() {
        DashboardController dc = new DashboardController();
        dc.onTrendingStocks();
    }

    @FXML
    public void btnViewAbout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/about.fxml"));
        final AboutController aboutController = new AboutController();
        try {
            Stage stage = new Stage();
            loader.setController(aboutController);
            Parent parent = loader.load();

            loader.setRoot(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("About Manual Inventory System");
            stage.setResizable(true);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
