package com.manual.productManagement;

import com.manual.AboutController;
import com.manual.exception.InvalidStockAmountException;
import com.manual.model.ProductCollections;
import com.manual.model.Product;
import com.manual.orders.OrdersController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddProductController {
    @FXML
    private TextField txtEngineCapacity;

    @FXML
    private ComboBox<String> txtTransmission;

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

    public void initialize() {
        List<String> carTransmission = new ArrayList<>();
        carTransmission.add("Automatic");
        carTransmission.add("Manual");
        this.txtTransmission.setItems(FXCollections.observableArrayList(carTransmission));
    }
    @FXML
    public void fileBrowse() throws IOException, URISyntaxException {
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
        FileUtils.copyFile(
                FileUtils.getFile(f.getPath()),
                FileUtils.getFile("src/main/resources/cars/"+f.getName()),
                true
        );

        FileUtils.copyFile(
                FileUtils.getFile(f.getPath()),
                FileUtils.getFile("target/classes/com.manual.main/cars/"+f.getName())
                , true);


    }

    @FXML
    public void addItem(){
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
            if(!pc.isQuantityValid(test)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please input a quantity that is greater than or equal to 20.");
                alert.setTitle("Error when specifying a quantity value");
                alert.setHeaderText("Invalid stock amount");
                alert.showAndWait();
            }

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

        //constructing product and adding to collection
        //binding attributes based on user input
        Product product = new Product(this.txtProductModel.getText(),
                this.txtProductBrand.getText(),
                this.txtProductDescription.getText(),
                Float.parseFloat(this.txtProductPrice.getText()),
                Float.parseFloat(this.txtEngineCapacity.getText()),
                this.txtTransmission.getValue(),
                Integer.parseInt(this.txtProductStock.getText()),
                this.txtImagePath.getText(),
                Integer.parseInt(txtProductPopularity.getText())
                );

        pc.addProductRecord(product);

        //success message after inputting data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully added a new item to inventory",ButtonType.OK);
        alert.setTitle("Removed product successfully");
        alert.setHeaderText("Successfully added a new item to inventory");
        alert.showAndWait();
        if(alert.getAlertType() == Alert.AlertType.CONFIRMATION) {
            this.closeDialog();
        }
    }

    @FXML
    public void closeDialog() {
        txtTransmission.getScene().getWindow().hide();
    }

    @FXML
    public void onExit() {
        txtTransmission.getScene().getWindow().hide();
    }

    @FXML
    public void onAbout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/about.fxml"));
        final AboutController aboutController = new AboutController();
        try {
            Stage stage = new Stage();
            loader.setController(aboutController);
            Parent parent = loader.load();

            loader.setRoot(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("About Manual Inventory System");
            stage.setResizable(true);
            stage.setMaximized(true);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
