package com.manual.model;

import com.manual.ManualDatabaseConnection;
import com.manual.model.Order;
import com.manual.orders.OrdersController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderCollections {

    private List<Order> orders;

    public OrderCollections(List<Order> orderList){
        this.orders = orderList;
    }

    public  OrderCollections(){ this(new ArrayList<>());}

    public List<Order> getOrders() {
        try{
            Connection con = ManualDatabaseConnection.getInstance().getConnection();

            ResultSet result = con.createStatement().executeQuery("SELECT * FROM orders");

            while(result.next()){
                Order order = new Order(
                        result.getInt("orderId"),
                        result.getInt("userId"),
                        result.getInt("productsId"),
                        result.getFloat("price"),
                        result.getDate("deliveryDate"),
                        result.getDate("orderDate")
                );
                orders.add(order);
            }
        } catch (SQLException e){
            System.err.println("Failed to get Orders: " + e.getMessage());
        }
        return orders;
    }
}
