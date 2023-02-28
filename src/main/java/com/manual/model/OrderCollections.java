package com.manual.model;

import com.manual.ManualDatabaseConnection;
import com.manual.model.Order;
import com.manual.orders.OrdersController;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                        result.getInt("ordersId"),
                        result.getInt("userId"),
                        result.getInt("productsId"),
                        result.getFloat("price"),
                        result.getDate("deliveryDate"),
                        result.getDate("orderDate"),
                        result.getBoolean("isProcessed")
                );
                orders.add(order);
                System.out.println(order.getDeliveryDate());
            }
        } catch (SQLException e){
            System.err.println("Failed to get Orders: " + e.getMessage());
        }
        return orders;
    }
    public Boolean status(Order od){
        SimpleDateFormat formatter= new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        Date date = new Date(System.currentTimeMillis());
        return od.getDeliveryDate().equals(date);
    }
}
