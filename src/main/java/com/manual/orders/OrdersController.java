package com.manual.orders;

import com.manual.model.OrderCollections;
import com.manual.model.ProductCollections;
import com.manual.model.Product;
import com.manual.model.Order;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
* @author Victory Mpokosa <200174572@aston.ac.uk>
* @version 1.0
* @since 23/02/23
* */
public class OrdersController {
    @FXML
    private TableView<Order> tblOrders;

    @FXML
    private TableColumn<Order, Integer> colUserID;

    @FXML
    private TableColumn<Order, Integer> colProductID;

    @FXML
    private TableColumn<Order, String> colDeliveryDate;

    @FXML
    private TableColumn<Order, Boolean> colStatus;

    @FXML
    private TableColumn<Order, Integer> colStock;

    @FXML
    private ImageView detailImg;

    @FXML
    private Label orderDetail;

    public void initialize() {
        load();
    }

    @FXML
    public void btnDelivered() {

    }

    public void load() {
        OrderCollections oc = new OrderCollections();
        ArrayList<Order> orders = new ArrayList<>(oc.getOrders());
        ObservableList<Order> ord = FXCollections.observableArrayList(orders);
        System.out.println(oc.getOrders());
        // colDeliveryDate.setCellValueFactory(cell -> cell.getValue().getDeliveryDate());
        colStatus.setCellValueFactory(cell -> cell.getValue().getStatus());
        colStock.setCellValueFactory(cell -> cell.getValue().getStock().asObject());
        colProductID.setCellValueFactory(cell -> cell.getValue().getProductIdProperty().asObject());
        colUserID.setCellValueFactory(cell -> cell.getValue().getUserIdProperty().asObject());

        tblOrders.setItems(ord);
        detailImg = new ImageView();

    }

    @FXML
    public void detailView(MouseEvent event) {
        if(event.getClickCount() == 2) {
            System.out.println("1");
            Image image = new Image("/com.manual.main/audi.jpg");
            System.out.println(image);
            detailImg.setImage(image);
            System.out.println(detailImg.getImage());
        }
    }
}
