package com.manual.productManagement;

import com.manual.model.ProductCollections;
import com.manual.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * List all items that are within inventory and carries out operation within two-dimensional
 * table.
 * @author Ibrahim Ahmad <210029073@aston.ac.uk>
 * @version 1.2
 * */
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
    private ImageView image;

    @FXML
    private TableColumn<Product, Float> colEngineSize;

    @FXML
    private TableColumn<Product, String> colTransmission;

    @FXML
    private ChoiceBox<String> genres;

    public void initialize() throws IOException {
        btnLoad();
        image.setAccessibleText("Please select an image");
        setGenres();
    }

    public void setGenres(){
        ObservableList<String> oL = FXCollections.observableArrayList();
        ProductCollections pc = new ProductCollections();
        ArrayList<String> filters = new ArrayList<>();
        filters = (ArrayList<String>) pc.unique();
        oL.add("All Products");
        for(int i =0;i<=pc.unique().size()-1; i++){
            oL.add(filters.get(i));
        }
        genres.setItems(oL);
    }

    @FXML
    public void prdFilter(){
        if(genres.getValue() != null){
            ProductCollections productCollections = new ProductCollections();
            ArrayList<Product> data = new ArrayList<>(productCollections.getProducts(genres.getValue()));
            if(genres.getValue().equals("All Products")){
               btnLoad();
            } else {
                btnLoad(data);
            }

        }
    }

    @FXML
    private void btnLoad() {
        ObservableList<Product> prd = FXCollections.observableArrayList(makeData());

        //dummy product
//        prd.add(new Product("330D", "BMW", "BMW E46 330D DIESEL 3.0 Litre V6 Engine", 1023.98F, 12, "na.img", 500));

        colBrand.setCellValueFactory(cell -> cell.getValue().brandProperty());
        colModel.setCellValueFactory(cell -> cell.getValue().modelProperty());
        colDesc.setCellValueFactory(cell -> cell.getValue().productDescProperty());
        colPrice.setCellValueFactory(cell -> cell.getValue().productPriceProperty().asObject());
        colEngineSize.setCellValueFactory(cell -> cell.getValue().engineCapacityProperty().asObject());
        colTransmission.setCellValueFactory(cell -> cell.getValue().transmissionProperty());
        colStockLevel.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());
        colImagePath.setCellValueFactory(cell -> cell.getValue().imageFilePathProperty());
        colLikes.setCellValueFactory(new PropertyValueFactory<>("Likes"));

        tblProduct.setItems(prd);
    }

    @FXML
    private void btnLoad(ArrayList<Product> data) {
        ObservableList<Product> prd = FXCollections.observableArrayList(data);

        //dummy product
//        prd.add(new Product("330D", "BMW", "BMW E46 330D DIESEL 3.0 Litre V6 Engine", 1023.98F, 12, "na.img", 500));

        colBrand.setCellValueFactory(cell -> cell.getValue().brandProperty());
        colModel.setCellValueFactory(cell -> cell.getValue().modelProperty());
        colDesc.setCellValueFactory(cell -> cell.getValue().productDescProperty());
        colPrice.setCellValueFactory(cell -> cell.getValue().productPriceProperty().asObject());
        colEngineSize.setCellValueFactory(cell -> cell.getValue().engineCapacityProperty().asObject());
        colTransmission.setCellValueFactory(cell -> cell.getValue().transmissionProperty());
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
        if(tblProduct.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an item to delete from inventory");
            alert.setTitle("Failed to remove item");
            alert.setHeaderText("An error occurred when removing an item");
            alert.showAndWait();
        }
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
    /**
     * This should change the image of the product that the user
     * selects
     * @author Ibrahim Ahmad <210029073@aston.ac.uk>
     * @date 26/02/2023
     * */
    public void changeImage() {
        Product p = tblProduct.getSelectionModel().getSelectedItem();
        Image image1 = new Image(getClass().getResource("/com.manual.main/cars/"+p.getImageFilePath()).toString());
        image.setImage(image1);
        image.setPreserveRatio(true);
        image.setAccessibleText(p.getProductBrand() +" " + p.getProductModel());
    }

    @FXML
    public void btnUpdate() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/update_stock.fxml"));
        Product target = tblProduct.getSelectionModel().getSelectedItem();
        if(tblProduct.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an item from inventory");
            alert.setTitle("Error occurred when updating");
            alert.setHeaderText("An error occurred when updating a stock");
            alert.showAndWait();
        }
        final ProductUpdateController productUpdateController = new ProductUpdateController(target);
        try {
            Stage stage = new Stage();
            loader.setController(productUpdateController);
            Parent parent = loader.load();
            loader.setRoot(parent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Update a stock from inventory");
            stage.setResizable(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();

            btnLoad();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> makeData(){
        ProductCollections productCollections = new ProductCollections();
        return new ArrayList<>(productCollections.getProducts());
    }
}
