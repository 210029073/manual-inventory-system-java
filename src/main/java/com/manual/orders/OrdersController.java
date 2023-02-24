package com.manual.orders;

import com.manual.model.OrderCollections;
import com.manual.model.ProductCollections;
import com.manual.model.Product;
import com.manual.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
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
    private TableColumn<Order, Date> colDevliveryDate;

    @FXML
    private TableColumn<Order, String> colStatus;

    @FXML
    private TableColumn<Order, String> colStock;

    // @FXML
    // private ImageView orderImg;

    @FXML
    private Label orderDetail;

    public void initalize() {

        OrderCollections oc = new OrderCollections();
        ArrayList<Order> orders = new ArrayList<>(oc.getOrders());
        ObservableList<Order> ord = FXCollections.observableArrayList(orders);

        colDevliveryDate.setCellValueFactory(new PropertyValueFactory<>(""));

        tblOrders.setItems(ord);
    }

    @FXML
    public void btnDelivered() {

    }

}
