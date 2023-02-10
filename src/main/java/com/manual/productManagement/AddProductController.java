package com.manual.productManagement;

import com.manual.collections.ProductCollections;
import com.manual.model.Product;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
        FileChooser fileChooser = new FileChooser();

            Stage stage = new Stage();
            Parent parent = txtProductModel.getParent().getParent();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add a car");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();

        System.out.println("Coming soon...");
    }

    @FXML
    public void addItem() {
        ProductCollections pc = new ProductCollections();
        Product product = new Product(this.txtProductModel.getText(),
                this.txtProductBrand.getText(),
                this.txtProductDescription.getText(),
                Float.parseFloat(this.txtProductPrice.getText()),
                Integer.parseInt(this.txtProductStock.getText()),
                this.txtImagePath.getText(),
                Integer.parseInt(txtProductPopularity.getText())
                );
        pc.addProductRecord(product);
    }

    @FXML
    public void closeDialog() {
        txtProductName.getScene().getWindow().hide();
    }
}
