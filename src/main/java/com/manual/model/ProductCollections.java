package com.manual.model;
import com.manual.ManualDatabaseConnection;
import com.manual.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCollections {
    private List<Product> products;

    private Product productShow;

    public ProductCollections(List<Product> productList) {
        this.products = productList;
    }

    public ProductCollections() {
        this(new ArrayList<>());
    }

    public void addProductRecord(Product product) {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(`model`, `brand`, `description`, `price`,`engine_capacity`,`transmission`, `stock`, `image`, `likes`)" + "VALUE(?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductModel());
            preparedStatement.setString(2, product.getProductBrand());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setFloat(4, product.getProductPrice());
            preparedStatement.setFloat(5, product.getEngineCapacity());
            preparedStatement.setString(6, product.getTransmission());
            preparedStatement.setInt(7, product.getQuantity());
            preparedStatement.setString(8, product.getImageFilePath());
            preparedStatement.setInt(9,product.getLikes());
            preparedStatement.executeUpdate();
            System.out.println("Successfully executed SQL Statement");
        }

        catch (SQLException e) {
            System.err.println("Failed to add a record: " + e.getMessage());
        }
    }

    public void updateProductRecord(Product product) {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            PreparedStatement sql = connection.prepareStatement("UPDATE products SET `stock` = ?, `model` = ?, `brand` = ?, `description` = ?, `price` = ?, `likes` = ?, `image` = ?, `engine_capacity` = ?,`transmission` = ? WHERE `productsId` = ?");
            sql.setInt(1, product.getQuantity());
            sql.setString(2, product.getProductModel());
            sql.setString(3, product.getProductBrand());
            sql.setString(4, product.getProductDescription());
            sql.setFloat(5, product.getProductPrice());
            sql.setInt(6, product.getLikes());
            sql.setString(7, product.getImageFilePath());
            sql.setFloat(8, product.getEngineCapacity());
            sql.setString(9, product.getTransmission());
            sql.setInt(10, product.getId());
            sql.executeUpdate();
            System.out.println("Successfully updated a record");
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProductRecord(Product target) {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM products WHERE" + " model = ?");
            preparedStatement.setString(1, target.getProductModel());
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted a record.");
        }

        catch(SQLException sqlException) {
            System.out.println("Failed to delete an item: " + sqlException.getMessage());
        }
    }

    public List<Product> getProducts() {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getFloat("engine_capacity"),
                        resultSet.getString("transmission"),
                        resultSet.getInt("stock"),
                        resultSet.getString("image"),
                        resultSet.getInt("likes")
                );
                product.setID(resultSet.getInt("productsID"));
                products.add(product);
            }
        }
        catch (SQLException e) {
            System.err.println("Failed to add a record: " + e.getMessage());
        }
        return products;
    }

    public List<Product> insufficientStocks() {

        ArrayList<Product> lowStockQuanity = new ArrayList<>();

        for(Product p : getProducts()) {
            if(p.getQuantity() < 10) {
                lowStockQuanity.add(p);
            }
        }

        return lowStockQuanity;
    }

    public int totalStockInInventory() {
        int total = 0;
        for(Product p : getProducts()) {
            total += p.getQuantity();
        }

        return total;
    }
/**
 * @author Victory Mpokosa
 * @param productId
 * @return Unique product x which has been searched for in the products table*/
    public Product showProduct(int productId) {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM products WHERE productsId ="+productId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()){
                return new Product(
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getFloat("engine_capacity"),
                        resultSet.getString("transmission"),
                        resultSet.getInt("stock"),
                        resultSet.getString("image"),
                        resultSet.getInt("likes")
                );
            }
            } catch (SQLException e) {
            System.err.println("Product doesnt exist "+ e);
        }
    return productShow;}

    public List<Product> getProducts(String brd) {
        try {
            unique();
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM products WHERE brand = ?");
            pstmt.setString(1,brd);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price"),
                        resultSet.getFloat("engine_capacity"),
                        resultSet.getString("transmission"),
                        resultSet.getInt("stock"),
                        resultSet.getString("image"),
                        resultSet.getInt("likes")
                );
                product.setID(resultSet.getInt("productsID"));
                products.add(product);
            }
        }
        catch (SQLException e) {
            System.err.println("Failed to add a record: " + e.getMessage());
        }
        return products;
    }

    public List<String> unique(){
        ArrayList<String> result = new ArrayList<>();
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();

            ResultSet resultSet = connection.createStatement().executeQuery("SELECT DISTINCT brand FROM products");
            while(resultSet.next()){
                result.add(resultSet.getString("brand"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
