package com.manual.dashboard;

import com.manual.model.Order;
import com.manual.model.OrderCollections;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrendingSalesController {

    @FXML
    private VBox container;

    private HashMap<String, Number> totalCarBrand;

    private HashMap<String, XYChart.Series<String, Number>> dataSets;

    private ProductCollections productCollections;

    public TrendingSalesController() {
        this.totalCarBrand = new HashMap<>();
        this.dataSets = new HashMap<>();
        this.productCollections = new ProductCollections();

    }

    public void initialize() {
        List<String> xData = new ArrayList<>();

        prepareData(xData);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Mostly Brought");
        ObservableList<String> xAxis = FXCollections.observableArrayList(xData);
        CategoryAxis xCarAxis = new CategoryAxis(xAxis);
        xCarAxis.setLabel("Cars");
        BarChart<String, Number> barChart = new BarChart<>(xCarAxis, yAxis);
        barChart.setTitle("Trending Cars");
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        OrderCollections orderCollections = new OrderCollections();

        plotData(barChart, orderCollections);

        container.getChildren().add(barChart);
//        barChart.getData().add();
    }

    private void plotData(BarChart<String, Number> barChart, OrderCollections orderCollections) {
        for (Order o : orderCollections.getPastOrders()) {
            XYChart.Series productBrand = dataSets.get(o.getProduct().getProductBrand());

            productBrand.getData().add(new XYChart.Data<>(o.getProduct().getProductBrand(), totalCarBrand.get(o.getProduct().getProductBrand())));

            int oldValue = (int) totalCarBrand.get(o.getProduct().getProductBrand());
            totalCarBrand.replace(o.getProduct().getProductBrand(), oldValue + 1);

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
    public void btnExit() {

    }

}
