package com.manual.dashboard;

import com.manual.ManualDatabaseConnection;
import com.manual.model.OrderCollections;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
import com.manual.orders.OrdersController;
import com.manual.productManagement.ProductListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

public class DashboardController {

    @FXML
    private Label lblStockTest;
    @FXML
    private VBox pastOrderContainer;

    @FXML
    private Text txtPendingOrderMsg;

    @FXML
    private Text txtQuantityPendingOrder;

    @FXML
    private Button btnProcessOrder;

    @FXML
    private Text txtOrdersApprovedMsg;

    @FXML
    private Text txtOrdersApprovedQuantity;

    @FXML
    private Button btnViewPastOrders;

    @FXML
    private VBox stockLevelContainer;

    @FXML
    private Text txtStockLevelMsg;

    @FXML
    private Text txtStockLevelQuantity;

    @FXML
    private Button btnUpdateStock;

    @FXML
    private Text txtTotalStocks;

    public void initialize() {
        ProductCollections pc = new ProductCollections();

        Integer totalStocks = pc.totalStockInInventory();
        txtTotalStocks.setText(totalStocks.toString());

        if(pc.insufficientStocks().size() != 0) {


            String lowStocks = "";
            Iterator<Product> productIterator = pc.insufficientStocks().iterator();
            while (productIterator.hasNext()) {
                lowStocks += productIterator.next().getProductBrand() + " " + productIterator.next().getProductModel() + " with quantity of: " + productIterator.next().getQuantity() + "\n";
            }
            this.txtStockLevelMsg.setText("There is insufficient levels of stock that needs your attention.");
            this.txtStockLevelQuantity.setText(lowStocks);
            this.lblStockTest.setVisible(false);
            this.stockLevelContainer.setStyle("-fx-background-color: red; -fx-border-radius: 25%");

            OrderCollections oc = new OrderCollections();
            System.out.println(oc.getPendingOrders());
            if(oc.getPendingOrders().size() > 0) {
                this.txtPendingOrderMsg.setText("There is pending orders awaiting");
                this.txtQuantityPendingOrder.setText("There are at least " + oc.getPendingOrders().size() + " orders that are pending.");
            }
            if (oc.getPastOrders().size() > 0){
                this.txtOrdersApprovedMsg.setText("Orders approved");
                this.txtOrdersApprovedQuantity.setText("There is "+oc.getPastOrders().size()+" orders");
            }

        }

        else {
            this.txtStockLevelMsg.setText("Stock levels are sufficient.");
            this.txtStockLevelQuantity.setText("");
            this.lblStockTest.setVisible(false);
            this.stockLevelContainer.setStyle("-fx-background-color: limegreen; -fx-border-radius: 25%");
        }
    }

    @FXML
    public void btnExit() {
        this.stockLevelContainer.getScene().getWindow().hide();
    }

    @FXML
    public void pastOrders() {
        viewPastOrders();
    }

    @FXML
    public void viewPastOrders() {
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
    public void btnUpdateStock() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/list_products.fxml"));
        final ProductListController productListController = new ProductListController();
        try {
            Stage stage = new Stage();
            loader.setController(productListController);

            Parent parent = loader.load();

            loader.setRoot(parent);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Update Stock Level");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }

        catch (IOException e) {
            System.err.println("Could not find the fxml file");
            e.printStackTrace();
        }

        initialize();
    }
}
