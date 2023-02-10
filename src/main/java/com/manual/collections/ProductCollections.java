package com.manual.collections;
import com.manual.ManualDatabaseConnection;
import com.manual.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
}
