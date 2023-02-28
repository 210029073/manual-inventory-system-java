package com.manual.model;

import javafx.beans.property.*;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private ProductCollections pc;

    private Product prd;

    public Order(
            int id,
            int userId,
            int productId,
            float price,
            Date deliveryDate,
            Date orderDate,
            Boolean isProcessed
    ){
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.price = new SimpleFloatProperty(price);
        this.id = id;
        this.userId = new SimpleIntegerProperty(userId);
        this.productId = new SimpleIntegerProperty(productId);
        this.status = new SimpleBooleanProperty(isProcessed);
        pc = new ProductCollections();
        prd = pc.showProduct(productId);
    }

    public Order(){
        this(
                0,
                0,
                0,
                0.0F,
                null,
                null,
                null);
    }

    public StringProperty getDeliveryDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(deliveryDate);
        return new SimpleStringProperty(strDate);
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

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

    /**
     * @author Victory Mpokosa
     * @return Unique product x which has been searched for in the products table
     * */
    public int getProductID(){
        return prd.getId();
    }

    public IntegerProperty getUserIdProperty() {
        return this.userId;
    }

    public IntegerProperty getProductIdProperty() {
        return this.productId;
    }

    public IntegerProperty getStock() {
        return prd.quantityProperty();
    }
}
