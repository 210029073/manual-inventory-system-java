package com.manual.login;

import com.manual.ManualDatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginController {
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void loginConfirmation() {
        try {
            //checks if login is valid
            ManualDatabaseConnection mdb = ManualDatabaseConnection.getInstance();
            Connection connection = mdb.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String email = resultSet.getString(5);
                String password = resultSet.getString(6);
                System.out.println(password);
                String inputtedPassword = txtPassword.getText();
                boolean isValid = BCrypt.checkpw(inputtedPassword, password);
                System.out.println(isValid);
                if(txtUsername.getText().equals(email)) {
                    System.out.println("Correct member");
                }

                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cannot find the employee specified in your search criteria.", ButtonType.OK);
                    alert.setTitle("Error: Cannot find employee");
                    alert.setHeaderText("Cannot find employee");
                    alert.showAndWait();
                    if(alert.getResult() == (ButtonType.OK)) {
                        alert.close();
                    }
                }

            }

            //database must be connected
            //second check, checks if they are an admin or employee user
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeLogin() {
        System.exit(0);
    }

    public static void main(String[] args) {
        try {
            //checks if login is valid
            ManualDatabaseConnection mdb = ManualDatabaseConnection.getInstance();
            Connection connection = mdb.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String email = resultSet.getString(5);
                String password = resultSet.getString(6);
                String inputtedPassword = "1234";
//                boolean isValid = BCrypt.checkpw(inputtedPassword, password);
                if("210029073@aston.ac.uk".equals(email)) {
                    System.out.println("Correct member");
                }

                else {
                    System.err.println("Does not exist");
                }

            }

            //database must be connected
            //second check, checks if they are an admin or employee user
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
