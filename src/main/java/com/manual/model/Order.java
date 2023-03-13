package com.manual.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;


import java.util.Date;

/**
 * Models a generic order object
 * @author Victory Mpokosa <200174572>
 * @since 23/02/2023
 * */
public class Order {
    private final Boolean isProcessed;

    private Integer id2;
    private IntegerProperty id;

    private IntegerProperty userId;

    private IntegerProperty productId;

    private FloatProperty price;

    private Date deliveryDate;

    private Date orderDate;

    private BooleanProperty status;

    private String process;

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
        this.id = new SimpleIntegerProperty(id);
        this.id2 = id;
        this.userId = new SimpleIntegerProperty(userId);
        this.productId = new SimpleIntegerProperty(productId);
        this.status = new SimpleBooleanProperty(isProcessed);
        pc = new ProductCollections();
        prd = pc.showProduct(productId);
        process = statusCal(isProcessed);
        this.isProcessed =isProcessed;
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
        return new SimpleStringProperty(deliveryDate.toString());
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public SimpleIntegerProperty getIdProperty() { return new SimpleIntegerProperty(id.getValue()); }

    public Integer getId(){ return id2;}

    public Integer getProductId() { return productId.getValue(); }

    public Product getProduct() { return prd;}

    public Integer getUserId() { return userId.getValue();}

    public Date getOrderDate() { return orderDate; }

    public BooleanProperty getStatus() {return this.status;}

    public void setStatus(BooleanProperty status) {this.status = status; }

    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public void setId(int id) { this.id.set(id); }

    public void setProductId(int productId) { this.productId.set(productId); }

    public void setUserId(int userId) { this.userId.set(userId); }

    public void setPrice(float price) { this.price.set(price); }

    public FloatProperty getPriceProperty() {
        return price;
    }

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

    public String statusCal(Boolean st){
        if(st){
            return "Delivered";
        }
        return "Not Delivered "+"\n"+"Expected: "+deliveryDate;
    }

    public String toString() {
        return ("Model: "+prd.getProductModel()+"\n"
                +"Description: "+prd.getProductDescription()+"\n"
                +"Stock: "+prd.getQuantity()+"\n"
                +"OrderId: "+id2+"\n"
                +"Delivery status: "+process);
    }

    public Image getImage() {
        return new Image("/com.manual.main/"+prd.getImageFilePath());
    }

    public Boolean isProcessed(Boolean result){ return result;}
}
