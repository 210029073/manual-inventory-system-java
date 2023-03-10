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
    private TableColumn<Order, String> colDevliveryDate;

    @FXML
    private TableColumn<Order, Boolean> colStatus;

    @FXML
    private TableColumn<Order, Integer> colStock;

    @FXML
    private TableColumn<Order, Integer> colProductId;

    @FXML
    private ImageView detailImg;

    @FXML
    private Label orderDetail;

    @FXML
    private ListView<Order> pendingOrds;

    @FXML
    private ListView<Order> pastOrds;

    @FXML
    private Label sizePast;

    @FXML
    private Label sizePending;



    public void initialize() {
        load();
    }

    @FXML
    public void btnDelivered() {
        OrderCollections oc = new OrderCollections();
        oc.updateOrders(tblOrders.getSelectionModel().getSelectedItem(),true);
        load();
    }

    @FXML
    public void btnNot(){
        OrderCollections oc = new OrderCollections();
        oc.updateOrders(tblOrders.getSelectionModel().getSelectedItem(),false);
        load();
    }

    public void load() {
        OrderCollections oc = new OrderCollections();
        ArrayList<Order> orders = new ArrayList<>(oc.getOrders());
        ObservableList<Order> ord = FXCollections.observableArrayList(orders);
        ObservableList<Order> ods = FXCollections.observableArrayList(oc.getPendingOrders());
        ObservableList<Order> past = FXCollections.observableArrayList(oc.getPastOrders());
        colDevliveryDate.setCellValueFactory(cell -> cell.getValue().getDeliveryDate());
        colStatus.setCellValueFactory(cell -> cell.getValue().getStatus());
        colStock.setCellValueFactory(cell -> cell.getValue().getStock().asObject());
        colProductID.setCellValueFactory(cell -> cell.getValue().getProductIdProperty().asObject());
        colUserID.setCellValueFactory(cell -> cell.getValue().getUserIdProperty().asObject());
        colProductId.setCellValueFactory(cell -> cell.getValue().getIdProperty().asObject());

        tblOrders.setItems(ord);
        pendingOrds.setItems(ods);
        pastOrds.setItems(past);
        sizePast.setText(" "+past.size()+" ");
        sizePending.setText(" "+ods.size()+" ");
        orderDetail.setText("Description");
        Image image = new Image("/com.manual.main/car.jpg");
        detailImg.setImage(image);

    }

    @FXML
    public void detailView(MouseEvent event) {
        detailImg.setImage(tblOrders.getSelectionModel().getSelectedItem().getImage());
        orderDetail.setText(tblOrders.getSelectionModel().getSelectedItem().toString());
            // System.out.println(ords.getId());

        }
}
