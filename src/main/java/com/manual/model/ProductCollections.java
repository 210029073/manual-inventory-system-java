package com.manual.model;
import com.manual.ManualDatabaseConnection;
import com.manual.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCollections {
    private List<Product> products;

    public ProductCollections(List<Product> productList) {
        this.products = productList;
    }

    public ProductCollections() {
        this(new ArrayList<>());
    }

    public void addProductRecord(Product product) {
        try {
            Connection connection = ManualDatabaseConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(`model`, `brand`, `description`, `price`, `stock`, `image`, `likes`)" + "VALUE(?, ?, ?, ? ,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductModel());
            preparedStatement.setString(2, product.getProductBrand());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setFloat(4, product.getProductPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setString(6, product.getImageFilePath());
            preparedStatement.setInt(7,product.getLikes());
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
            PreparedStatement sql = connection.prepareStatement("UPDATE products SET `stock` = ?, `model` = ?, `brand` = ?, `description` = ?, `price` = ?, `likes` = ?, `image` = ? WHERE `productsId` = ?");
            sql.setInt(1, product.getQuantity());
            sql.setString(2, product.getProductModel());
            sql.setString(3, product.getProductBrand());
            sql.setString(4, product.getProductDescription());
            sql.setFloat(5, product.getProductPrice());
            sql.setInt(6, product.getLikes());
            sql.setString(7, product.getImageFilePath());
            sql.setInt(8, product.getId());
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

    public int productAmount() {
        int quantity = 0;

        for(Product p : getProducts()) {
            quantity += 1;
        }

        return quantity;
    }

    public int totalStockInInventory() {
        int total = 0;
        for(Product p : getProducts()) {
            total += p.getQuantity();
        }

        return total;
    }
}
