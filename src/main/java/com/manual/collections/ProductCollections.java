package com.manual.collections;
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
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setString(3, product.getProductBrand());
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

                products.add(product);
            }

        }

        catch (SQLException e) {
            System.err.println("Failed to add a record: " + e.getMessage());
        }

        return products;

    }
}
