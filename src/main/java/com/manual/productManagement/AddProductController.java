package com.manual.productManagement;

import com.manual.collections.ProductCollections;
import com.manual.model.Product;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
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
    public void fileBrowse() throws IOException {
        FileChooser fileChooser = new FileChooser();

            Stage stage = new Stage();
            Parent parent = txtProductModel.getParent().getParent();
            VBox vBox = new VBox();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add a car");
            stage.setResizable(false);

            Scene scene = new Scene(vBox);
            File f = fileChooser.showOpenDialog(vBox.getScene().getWindow());
            if(f == null) {}
            else txtImagePath.setText(f.getName());
//            vBox.getChildren().add();
//            stage.setScene(scene);
//            stage.showAndWait();

        System.out.println("Coming soon...");
    }

    @FXML
    public void addItem() {
        ProductCollections pc = new ProductCollections();

        //validations
        try{
            Float test = Float.parseFloat(txtProductPrice.getText());
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the price in two decimal places like 0.00");
            alert.setTitle("Error occurred while parsing stock price");
            alert.setHeaderText("An error occurred when processing the price");
            alert.showAndWait();
            e.printStackTrace();
        }

        try{
            Integer test = Integer.parseInt(txtProductStock.getText());
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the stock value in numeric.");
            alert.setTitle("Error occurred while parsing stock value");
            alert.setHeaderText("An error occurred when processing the stock value");
            alert.showAndWait();
            e.printStackTrace();
        }

        try{
            Integer test = Integer.parseInt(txtProductPopularity.getText());
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter the popularity in numeric");
            alert.setTitle("Error occurred while parsing popularity");
            alert.setHeaderText("An error occurred when processing the popularity value");
            alert.showAndWait();
            e.printStackTrace();
        }
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
