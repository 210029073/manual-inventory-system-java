package com.manual.model;

/**
 * Models a generic car object, that can be enhanced using inheritance
 * @author Ibrahim Ahmad (210029073)
 * @since 10/02/2023
 * */
public class Product {
    private int id;
    private String productModel;
    private String productBrand;
    private String productDescription;
    private float productPrice;
    private int quantity;
    private String imageFilePath;
    private int likes;

    /**
     * Constructs a product populated with its details.
     * @author Ibrahim Ahmad (210029073)*/
    public Product(
            String productModel,
            String productBrand,
            String productDescription,
            float productPrice,
            int quantity,
            String imageFilePath,
            int likes) {

        this.productModel = productModel;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.imageFilePath = imageFilePath;
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
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
