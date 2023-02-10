package com.manual.productManagement;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddProductController {
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtProductModel;
    @FXML
    private TextArea txtProductDescription;
    @FXML
    private TextField txtProductBrand;
    @FXML
    private TextField txtProductPrice;
    @FXML
    private TextField txtProductStock;
    @FXML
    private TextField txtImagePath;
    @FXML
    private TextField txtProductPopularity;

    @FXML
    public void fileBrowse() {
        System.out.println("Coming soon...");
    }

    @FXML
    public void addItem() {

    }

    @FXML
    public void closeDialog() {
        txtProductName.getScene().getWindow().hide();
    }
}
