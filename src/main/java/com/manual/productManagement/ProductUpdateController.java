package com.manual.productManagement;

import com.manual.AboutController;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
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
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ProductUpdateController {
    @FXML
    private TextField txtProductID;
    @FXML
    private TextField txtProductModel;

    @FXML
    private TextArea txtProductDescription;

    @FXML
    protected TextField txtProductBrand;
    @FXML
    private TextField txtProductPrice;
    @FXML
    private TextField txtProductStock;
    @FXML
    private TextField txtImagePath;
    @FXML
    private TextField txtProductPopularity;
    @FXML
    private TextField txtEngineCapacity;
    @FXML
    private ComboBox<String> txtTransmission;

    private Product candidate;

    public void initialize() {
        List<String> carTransmission = new ArrayList<>();
        carTransmission.add("Automatic");
        carTransmission.add("Manual");
        carTransmission.add("Semi-Auto");
        this.txtTransmission.setItems(FXCollections.observableArrayList(carTransmission));
        txtProductID.setText(Integer.toString(candidate.getId()));
        txtProductModel.setText(candidate.getProductModel());
        txtProductDescription.setText(candidate.getProductDescription());
        txtProductBrand.setText(candidate.getProductBrand());
        txtProductStock.setText(Integer.toString(candidate.getQuantity()));
        txtProductPopularity.setText(Integer.toString(candidate.getLikes()));
        txtProductPrice.setText(Float.toString(candidate.getProductPrice()));
        txtEngineCapacity.setText(Float.toString(candidate.getEngineCapacity()));
        txtTransmission.setValue(candidate.getTransmission());
        txtImagePath.setText(candidate.getImageFilePath());
    }


    public ProductUpdateController(Product candidate) {
        this.candidate = candidate;
    }


    @FXML
    public void fileBrowse() throws IOException, URISyntaxException {
        FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        Parent parent = txtProductModel.getParent().getParent();
        VBox vBox = new VBox();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Browse for car image");
        stage.setResizable(false);

        Scene scene = new Scene(vBox);
        File f = fileChooser.showOpenDialog(vBox.getScene().getWindow());
        String previousImage = txtImagePath.getText();

        if(f == null) {}
        else {
            txtImagePath.setText(f.getName());
            System.out.println(f.getPath());
            FileUtils.copyFile(
                    FileUtils.getFile(f.getPath()),
                    FileUtils.getFile("src/main/resources/com.manual.main/cars/"+f.getName())
                    , true);
            FileUtils.copyFile(
                    FileUtils.getFile(f.getPath()),
                    FileUtils.getFile("target/classes/com.manual.main/cars/"+f.getName())
                    , true);
            //destroy old file
            FileUtils.delete(
                    FileUtils.getFile("target/classes/com.manual.main/cars/"+previousImage)
            );
            FileUtils.delete(
                    FileUtils.getFile("src/main/resources/com.manual.main/cars/"+previousImage)
            );
        }
    }

    @FXML
    public void updateItem() throws URISyntaxException {
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

        //update date in product
        this.candidate.setLikes(Integer.parseInt(txtProductPopularity.getText()));
        Product p = new Product(txtProductModel.getText(), txtProductBrand.getText(),
                txtProductDescription.getText(), Float.parseFloat(txtProductPrice.getText()),
                Float.parseFloat(txtEngineCapacity.getText()), txtTransmission.getValue(),
                Integer.parseInt(txtProductStock.getText()), txtImagePath.getText(), Integer.parseInt(txtProductPopularity.getText()));
        p.setID(candidate.getId());
        //update product record
        pc.updateProductRecord(p);

        //success message after inputting data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully added a new item to inventory", ButtonType.OK);
        alert.setTitle("Removed product successfully");
        alert.setHeaderText("Successfully added a new item to inventory");
        alert.showAndWait();
        if(alert.getAlertType() == Alert.AlertType.CONFIRMATION) {
            this.closeDialog();
        }
    }

    @FXML
    public void closeDialog() {
        txtProductID.getScene().getWindow().hide();
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
