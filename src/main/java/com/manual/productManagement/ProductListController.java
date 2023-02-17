package com.manual.productManagement;

import com.manual.collections.ProductCollections;
import com.manual.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ProductListController {
    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TableColumn<Product, String> colBrand;

    @FXML
    private TableColumn<Product, String> colModel;

    @FXML
    private TableColumn<Product, String> colDesc;

    @FXML
    private TableColumn<Product, Float> colPrice;

    @FXML
    private TableColumn<Product, Integer> colStockLevel;

    @FXML
    private TableColumn<Product, String> colImagePath;

    @FXML
    private TableColumn<Product, String> colLikes;

    @FXML
    private void btnLoad() {

        ProductCollections productCollections = new ProductCollections();
        ArrayList<Product> data = new ArrayList<>(productCollections.getProducts());
        ObservableList<Product> prd = FXCollections.observableArrayList(data);

        //dummy product
        prd.add(new Product("330D", "BMW", "BMW E46 330D DIESEL 3.0 Litre V6 Engine", 1023.98F, 12, "na.img", 500));

        colBrand.setCellValueFactory(cell -> cell.getValue().brandProperty());
        colModel.setCellValueFactory(cell -> cell.getValue().modelProperty());
        colDesc.setCellValueFactory(cell -> cell.getValue().productDescProperty());
        colPrice.setCellValueFactory(cell -> cell.getValue().productPriceProperty().asObject());
        colStockLevel.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
        colImagePath.setCellValueFactory(cell -> cell.getValue().imageFilePathProperty());
        colLikes.setCellValueFactory(new PropertyValueFactory<>("Likes"));


        tblProduct.setItems(prd);
    }

    @FXML
    public void btnClose() {
        tblProduct.getScene().getWindow().hide();
    }

    @FXML
    public void btnRemove() {
        Product target = tblProduct.getSelectionModel().getSelectedItem();
        ProductCollections pc = new ProductCollections();

        //confirmation before deleting
        Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION, "Are you sure you want to delete "+ target.getProductBrand() + " " + target.getProductModel() + " from inventory?", ButtonType.OK, ButtonType.CANCEL);
        deleteAlert.setTitle("Deleting an item from inventory");
        deleteAlert.setHeaderText("Are you sure you want to continue?");
        deleteAlert.showAndWait();

        if(deleteAlert.getResult() == ButtonType.OK) {
            pc.removeProductRecord(target);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Successfully deleted " + target.getProductBrand() + " " + target.getProductModel() + ".", ButtonType.OK);
            alert.setTitle("Removed product successfully");
            alert.setHeaderText("Successfully removed an item from inventory");
            alert.showAndWait();
            if(alert.getAlertType() == Alert.AlertType.CONFIRMATION) {
                btnLoad(); //reload table
            }
        }

        else{
            deleteAlert.close();
        }


//        System.out.println(target.getProductBrand() + " " + target.getProductModel());
    }

    @FXML
    public void btnUpdate() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/add_employee.fxml"));

        try {
            Stage stage = new Stage();
            Parent parent = loader.load();
            loader.setRoot(parent);
            loader.setController(new ProductUpdateController());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Update a stock from inventory");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("This is the update button from " + getClass().getSimpleName());
    }
}
