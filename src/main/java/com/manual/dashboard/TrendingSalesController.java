package com.manual.dashboard;

import com.manual.model.Order;
import com.manual.model.OrderCollections;
import com.manual.model.Product;
import com.manual.model.ProductCollections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TrendingSalesController {

    private final OrderCollections orderCollections;
    private final VBox annualSalesContainer;
    @FXML
    private VBox container;

    private HashMap<String, Number> totalCarBrand;

    private HashMap<String, XYChart.Series<String, Number>> dataSets;

    private ProductCollections productCollections;

    private HBox statisticsContainer;

    private Text txtAnnualSales;
    private Text txtAnnualSalesMsg;

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
        annualSalesContainer.getChildren().add(txtAnnualSalesMsg);
        annualSalesContainer.getChildren().add(this.txtAnnualSales);
        txtAnnualSales.setFont(new Font("Arial", 22));
        txtAnnualSales.setVisible(true);
        statisticsContainer.setVisible(true);
        this.statisticsContainer.getChildren().add(annualSalesContainer);
        this.container.getChildren().add(statisticsContainer);
    }

    private void prepareTrendingSalesGraph() {
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

        plotData(barChart, orderCollections);

        container.getChildren().add(barChart);
    }

    private void plotData(BarChart<String, Number> barChart, OrderCollections orderCollections) {
        Float salesVal = 0.00F;

        for (Order o : orderCollections.getPastOrders()) {
            XYChart.Series<String, Number> productBrand = dataSets.get(o.getProduct().getProductBrand());

            productBrand.getData().add(new XYChart.Data<>(o.getProduct().getProductBrand(), totalCarBrand.get(o.getProduct().getProductBrand())));

            int oldValue = (int) totalCarBrand.get(o.getProduct().getProductBrand());
            totalCarBrand.replace(o.getProduct().getProductBrand(), oldValue + 1);
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

    }

    @FXML()
    public void viewMainMenu() {

    }

    @FXML
    public void viewPastOrders() {

    }

    @FXML
    public void viewAboutInfo() {
        
    }

    @FXML
    public void btnExit() {
        this.container.getScene().getWindow().hide();
    }

}
