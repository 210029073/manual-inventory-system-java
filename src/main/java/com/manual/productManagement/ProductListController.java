package com.manual.productManagement;

import com.manual.collections.ProductCollections;
import com.manual.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        pc.removeProductRecord(target);

        Alert deleteAlert = new Alert(Alert.AlertType.INFORMATION, "Are you sure you want to delete "+ target.getProductBrand() + " " + target.getProductModel() + " from inventory?", ButtonType.OK, ButtonType.CANCEL);
        deleteAlert.setTitle("Deleting an item from inventory");
        deleteAlert.setHeaderText("Are you sure you want to continue?");
        deleteAlert.showAndWait();

        if(deleteAlert.getResult() == ButtonType.OK) {
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


        System.out.println(target.getProductBrand() + " " + target.getProductModel());
        System.out.println("Remove an item. Coming Soon.");
    }
}
