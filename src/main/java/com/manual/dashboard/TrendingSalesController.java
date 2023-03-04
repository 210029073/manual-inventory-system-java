package com.manual.dashboard;

import com.manual.menu.MenuController;
import com.manual.model.Order;
import com.manual.model.OrderCollections;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
import com.manual.orders.OrdersController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TrendingSalesController {

    private OrderCollections orderCollections;
    private VBox annualSalesContainer;
    @FXML
    private VBox container;

    private HashMap<String, Number> totalCarBrand;

    private HashMap<String, XYChart.Series<String, Number>> dataSets;

    private ProductCollections productCollections;

    private HBox statisticsContainer;

    private Text txtAnnualSales;
    private Text txtAnnualSalesMsg;
    private List<String> xData;
    private ObservableList<String> xAxis;
    private CategoryAxis xCarAxis;
    private NumberAxis yAxis;
    private BarChart<String, Number> barChart;

    public TrendingSalesController() {
        this.totalCarBrand = new HashMap<>();
        this.dataSets = new HashMap<>();
        this.productCollections = new ProductCollections();

        orderCollections = new OrderCollections();
        this.txtAnnualSales = new Text();
        annualSalesContainer = new VBox();
        this.statisticsContainer = new HBox();
    }

    public void initialize() {
        prepareTrendingSalesGraph();

        //set text field
        setAnnualSalesText();
    }

    private void setAnnualSalesText() {
        annualSalesContainer.setSpacing(10);
        annualSalesContainer.paddingProperty().setValue(new Insets(25));
        txtAnnualSalesMsg = new Text("Annual Sale Summary");
        txtAnnualSalesMsg.setFont(new Font("Arial Bold", 24));
        if(!annualSalesContainer.getChildren().contains(txtAnnualSales) && !annualSalesContainer.getChildren().contains(txtAnnualSalesMsg)) {
            annualSalesContainer.getChildren().add(txtAnnualSalesMsg);
            annualSalesContainer.getChildren().add(this.txtAnnualSales);
            txtAnnualSales.setFont(new Font("Arial", 22));
            txtAnnualSales.setVisible(true);
        }

        if(!statisticsContainer.getChildren().contains(annualSalesContainer) && !container.getChildren().contains(statisticsContainer)) {
            statisticsContainer.setVisible(true);
            this.statisticsContainer.getChildren().add(annualSalesContainer);
            this.container.getChildren().add(statisticsContainer);
        }

    }

    private void prepareTrendingSalesGraph() {
        xData = new ArrayList<>();

        prepareData(xData);

        yAxis = new NumberAxis();
        yAxis.setLabel("Mostly Brought");
        if(xAxis != null) {
            xAxis = FXCollections.observableArrayList(new ArrayList<>());
            xCarAxis = new CategoryAxis(xAxis);
        }
        else {
            xAxis = FXCollections.observableArrayList(xData);
            xCarAxis = new CategoryAxis(xAxis);
            xCarAxis.setLabel("Cars");

            barChart = new BarChart<>(xCarAxis, yAxis);
            barChart.setTitle("Trending Cars");
            XYChart.Series<String, Number> data = new XYChart.Series<>();

            plotData(barChart, orderCollections);

            container.getChildren().add(barChart);
        }
    }

    private void plotData(BarChart<String, Number> barChart, OrderCollections orderCollections) {
        Float salesVal = 0.00F;

        for (Order o : orderCollections.getPastOrders()) {
            XYChart.Series<String, Number> productBrand = dataSets.get(o.getProduct().getProductBrand());
            int oldValue = (int) totalCarBrand.get(o.getProduct().getProductBrand());

            productBrand.getData().add(new XYChart.Data<>(o.getProduct().getProductBrand(), oldValue + 1));

//            int oldValue = dataSets.get(o.getProduct().getProductBrand()).getData().size();
            totalCarBrand.replace(o.getProduct().getProductBrand(), oldValue, oldValue + 1);
            if(LocalDate.parse(o.getOrderDate().toString()).getYear() == LocalDate.now().getYear()) {
                salesVal += o.getProduct().getProductPrice();
                this.txtAnnualSales.setText("£" + salesVal);
            }
            dataSets.replace(o.getProduct().getProductBrand(), productBrand);
            barChart.getData().remove(productBrand);
            barChart.getData().add(productBrand);
        }
    }

    private void prepareData(List<String> xData) {
        for (Product p : productCollections.getProducts()) {
            totalCarBrand.put(p.getProductBrand(), 0);
            XYChart.Series<String, Number> carSeries = new XYChart.Series<>();
            carSeries.setName(p.getProductBrand());
            dataSets.put(p.getProductBrand(), carSeries);
            xData.add(p.getProductBrand());

        }
    }

    @FXML
    public void refreshScreen() {
        this.container.getChildren().clear();
        this.totalCarBrand = new HashMap<>();
        this.dataSets = new HashMap<>();
        List<String> newCategory = new ArrayList<>();

        //prepare x axis
        for(Product p : productCollections.getProducts()) {
            if(!newCategory.contains(p.getProductBrand())) {
                totalCarBrand.put(p.getProductBrand(), 0);
                XYChart.Series<String, Number> carSeries = new XYChart.Series<>();
                carSeries.setName(p.getProductBrand());
                dataSets.put(p.getProductBrand(), carSeries);
                newCategory.add(p.getProductBrand());
            }
        }

        CategoryAxis newX = new CategoryAxis(FXCollections.observableArrayList(newCategory));
        newX.setLabel("Car Brands");
        NumberAxis newY = new NumberAxis();

        newY.setLabel("No. of Cars Sold");
        BarChart<String, Number> car = new BarChart<>(newX, newY);
        car.setTitle("Trending Cars");

        //prepare the y axis
        for (Order o : orderCollections.getPastOrders()) {
            XYChart.Series<String, Number> productBrand = dataSets.get(o.getProduct().getProductBrand());
            int oldValue = (int) totalCarBrand.get(o.getProduct().getProductBrand());

            productBrand.getData().add(new XYChart.Data<>(o.getProduct().getProductBrand(), oldValue + 1));

//            int oldValue = dataSets.get(o.getProduct().getProductBrand()).getData().size();
            totalCarBrand.replace(o.getProduct().getProductBrand(), oldValue, oldValue + 1);
            if(LocalDate.parse(o.getOrderDate().toString()).getYear() == LocalDate.now().getYear()) {
//                salesVal += o.getProduct().getProductPrice();
//                this.txtAnnualSales.setText("£" + salesVal);
            }
            dataSets.replace(o.getProduct().getProductBrand(), productBrand);
            car.getData().remove(productBrand);
            car.getData().add(productBrand);
        }
        container.getChildren().add(car);
    }

    @FXML
    public void viewPastOrders() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com.manual.main/orders.fxml"));
        final OrdersController oc = new OrdersController();
        try {
            Stage stage = new Stage();
            loader.setController(oc);
            Parent parent = loader.load();

            loader.setRoot(parent);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Manage Orders");
            stage.setResizable(true);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void viewAboutInfo() {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("About");
        stage.setResizable(false);

        VBox vbox = new VBox();

        String msg = "App used for managing inventory"
                + "\n" + "Used MySQL as Server Database." +
                "\n\n\n\n" + "Created by Team 21";

        Label lblMsg = new Label(msg);
        lblMsg.textAlignmentProperty().set(TextAlignment.CENTER);
        lblMsg.setPadding(new Insets(10,10,10,10));

        vbox.getChildren().add(lblMsg);

        Scene scene = new Scene(vbox, 300, 150);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void btnExit() {
        this.container.getScene().getWindow().hide();
    }

}
