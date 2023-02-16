package com.manual.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Models a generic car object, that can be enhanced using inheritance
 * @author Ibrahim Ahmad (210029073)
 * @since 10/02/2023
 * */
public class Product {
    private int id;
    private StringProperty productModel;
    private StringProperty productBrand;
    private StringProperty productDescription;
    private FloatProperty productPrice;
    private Integer quantity;
    private StringProperty imageFilePath;
    private int likes;

    /**
     * Constructs a product populated with its details.
     * @author Ibrahim Ahmad (210029073)*/
    public Product(
            String productModel,
            String productBrand,
            String productDescription,
            float productPrice,
            Integer quantity,
            String imageFilePath,
            int likes) {

        this.productModel = new SimpleStringProperty(productModel);
        this.productBrand = new SimpleStringProperty(productBrand);
        this.productDescription = new SimpleStringProperty(productDescription);
        this.productPrice = new SimpleFloatProperty(productPrice);
        this.quantity = quantity;
        this.imageFilePath = new SimpleStringProperty(imageFilePath);
        this.likes = likes;
    }

    /**
     * Constructs a product with no or very little details, though involves specifying an id
     * @author Ibrahim Ahmad (210029073)
     * */
    public Product() {
        this(
                null,
                null,
                null,
                0.0F,
                0,
                null,
                0
        );
    }

    public int getId() {
        return id;
    }

    public String getProductModel() {
        return productModel.getValue();
    }

    public StringProperty modelProperty() { return this.productModel; }

//    public void setProductModel(String productModel) {
//        this.productModel = productModel;
//    }

    public String getProductBrand() {
        return productBrand.getValue();
    }

    public StringProperty brandProperty() { return this.productBrand; }

//    public void setProductBrand(String productBrand) {
//        this.productBrand = productBrand;
//    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
    }

    public StringProperty productDescProperty() {
        return this.productDescription;
    }

    public float getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(float productPrice) {
        this.productPrice.set(productPrice);
    }

    public FloatProperty productPriceProperty() {
        return productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public IntegerProperty quantityProperty() {
        return new SimpleIntegerProperty(quantity);
    }

    public String getImageFilePath() {
        return imageFilePath.get();
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath.set(imageFilePath);
    }

    public StringProperty imageFilePathProperty() { return this.imageFilePath; }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
