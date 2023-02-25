package com.manual.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.Date;

/**
 * Models a generic order object
 * @author Victory Mpokosa <200174572>
 * @since 23/02/2023
 * */
public class Order {
    private int id;

    private IntegerProperty userId;

    private IntegerProperty productId;

    private FloatProperty price;

    private Date deliveryDate;

    private Date orderDate;

    private BooleanProperty status;

    public Order(
            int id,
            int userId,
            int productId,
            float price,
            Date deliveryDate,
            Date orderDate
    ){
        this.deliveryDate = new Date(String.valueOf(deliveryDate));
        this.orderDate = new Date(String.valueOf(orderDate));
        this.price = new SimpleFloatProperty(price);
        this.id = id;
        this.userId = new SimpleIntegerProperty(userId);
        this.productId = new SimpleIntegerProperty(userId);
        this.status = null;
    }

    public Date getDeliveryDate() { return deliveryDate; }

    public int getId() { return id; }

    public Integer getProductId() { return productId.getValue(); }



    public Integer getUserId() { return userId.getValue();}

    public Date getOrderDate() { return orderDate; }

    public BooleanProperty getStatus() {return this.status;}

    public void setStatus(BooleanProperty status) {this.status = status; }

    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public void setId(int id) { this.id = id; }

    public void setProductId(int productId) { this.productId.set(productId); }

    public void setUserId(int userId) { this.userId.set(userId); }

    public void setPrice(float price) { this.price.set(price); }

    public FloatProperty getPriceProperty() {
        return price;
    }

    /**
     * @author Victory Mpokosa
     * @return Unique product x which is associated with the current order which
     *         has been searched for in the products table
     * */
    public Product getProductID(){
        return  new ProductCollections().showProduct(productId.get());
    }

    /**
     * @author Victory Mpokosa
     * @param productId
     * @return Unique product x which has been searched for in the products table
     * */
    public Product getProductID(int productId){
        ProductCollections pc = new ProductCollections();
        return pc.showProduct(productId);
    }

    public IntegerProperty getUserIdProperty() {
        return this.userId;
    }

    public IntegerProperty getProductIdProperty() {
        return this.productId;
    }
}
