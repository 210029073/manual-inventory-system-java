package com.manual.model;

import com.manual.ManualDatabaseConnection;
import com.manual.model.Order;
import com.manual.orders.OrdersController;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            }
        } catch (SQLException e){
            System.err.println("Failed to get Orders: " + e.getMessage());
        }
        return orders;
    }

    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            ResultSet sql = connection.createStatement().executeQuery("SELECT * FROM orders WHERE isProcessed = false");
            while(sql.next()) {
                Order order = new Order(
                        sql.getInt("ordersId"),
                        sql.getInt("userId"),
                        sql.getInt("productsId"),
                        sql.getFloat("price"),
                        sql.getDate("deliveryDate"),
                        sql.getDate("orderDate"),
                        sql.getBoolean("isProcessed")
                );

                pendingOrders.add(order);
            }
        }

        catch (SQLException e) {
            System.err.println("Failed to get orders.");
            e.printStackTrace();
        }

        return pendingOrders;
    }

    public List<Order> getPastOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            ResultSet sql = connection.createStatement().executeQuery("SELECT * FROM orders WHERE isProcessed = true");
            while(sql.next()) {
                Order order = new Order(
                        sql.getInt("ordersId"),
                        sql.getInt("userId"),
                        sql.getInt("productsId"),
                        sql.getFloat("price"),
                        sql.getDate("deliveryDate"),
                        sql.getDate("orderDate"),
                        sql.getBoolean("isProcessed")
                );

                pendingOrders.add(order);
            }
        }

        catch (SQLException e) {
            System.err.println("Failed to get orders.");
            e.printStackTrace();
        }

        return pendingOrders;
    }
    public void updateOrders(Order ord, Boolean result){
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            PreparedStatement sql = connection.prepareStatement("UPDATE orders SET `isProcessed` = ? WHERE ordersId="+ord.getId());
            sql.setBoolean(1,ord.isProcessed(result));
            if(ord.getProduct().getQuantity() >0 && result){
                PreparedStatement sql2 = connection.prepareStatement("UPDATE products SET `stock` = ? WHERE productsId="+ord.getProductId());
                sql2.setInt(1, ord.getProduct().getQuantity()-1);
                sql2.executeUpdate();
                System.out.println("Stock Updated");
            }
            sql.executeUpdate();
            System.out.println("Done");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
