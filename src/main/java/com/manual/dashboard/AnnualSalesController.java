package com.manual.dashboard;

import com.manual.AboutController;
import com.manual.model.Order;
import com.manual.model.OrderCollections;
import com.manual.orders.OrdersController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
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
import java.util.Collections;
import java.util.List;

public class AnnualSalesController {

    @FXML
    private VBox container;

    private VBox graphContainer;

    private HBox statisticsSummaryContainer;

    private LineChart<String, Number> salesChart;

    private OrderCollections orderCollections;

    public void initialize() {
        this.graphContainer = new VBox();
        this.statisticsSummaryContainer = new HBox();
        this.orderCollections = new OrderCollections();
        //prepare its x axis
        List<String> xData = new ArrayList<>();

        //populate with this year's orders
        for(Order o : orderCollections.getPastOrders()) {
            if(LocalDate.parse(o.getOrderDate().toString()).getYear() == LocalDate.now().getYear() && !xData.contains(o.getOrderDate().toString())) {
                xData.add(o.getOrderDate().toString());
            }
        }


        Collections.sort(xData);

        Axis dateAxis = new CategoryAxis(FXCollections.observableArrayList(xData));
        dateAxis.setLabel("Dates");

        //prepare its y axis
        Axis profit = new NumberAxis();
        profit.setLabel("Sales profit gained in pounds.");

        //prepare data
        XYChart.Series<String, Number> annualSales = new XYChart.Series<>();
        for(Order order : orderCollections.getPastOrders()) {
            annualSales.getData().add(new XYChart.Data<>(order.getOrderDate().toString(), order.getProduct().getProductPrice()));
        }

        //create the graph
        this.salesChart = new LineChart<String, Number>(dateAxis, profit);
        this.salesChart.getData().add(annualSales);
        annualSales.setName("Amount gained in sale");
        this.salesChart.setTitle("Total no. of revenue gained in sales");
        this.graphContainer.getChildren().add(salesChart);
        this.container.getChildren().add(graphContainer);

        //prepare stat info
        //mean
        Text meanMsg = new Text("Average earned from sales");
        meanMsg.setFont(new Font("Arial Bold", 24));
        Float result = 0F;
        for(XYChart.Data data : annualSales.getData()) {
            result += (Float) data.getYValue();
        }
        Float temp = result;
//        result = temp / annualSales.getData().size();
        Text mean = new Text("£" + String.format("%.2f", result));
        mean.setFont(new Font("Arial", 22));
        VBox meanContainer = new VBox();
        meanContainer.getChildren().add(meanMsg);
        meanContainer.getChildren().add(mean);
        meanContainer.setSpacing(10);
        meanContainer.paddingProperty().setValue(new Insets(25));
        statisticsSummaryContainer.getChildren().add(meanContainer);

        //median
        Float median = 0F;
        if(annualSales.getData().size() > 0) {
            median = (Float) annualSales.getData().get(annualSales.getData().size()/2).getYValue();
        }
        Text medianMsg = new Text("Median");
        medianMsg.setFont(new Font("Arial Bold", 24));
        Text medianVal = new Text("£" + String.format("%.2f", median));
        medianVal.setFont(new Font("Arial", 22));
        VBox medianContainer = new VBox();
        medianContainer.getChildren().add(medianMsg);
        medianContainer.getChildren().add(medianVal);
        medianContainer.setSpacing(10);
        medianContainer.paddingProperty().setValue(new Insets(25));
        statisticsSummaryContainer.getChildren().add(medianContainer);

        //mode
        int maxN = annualSales.getData().size();
        float resultMode = 0;
        for(int i = 0; i < annualSales.getData().size(); i++) {
            int track = 0;
            for(int j = 0; j < annualSales.getData().size(); j++) {
                if(annualSales.getData().get(i).getYValue() == annualSales.getData().get(j).getYValue()) {
                    ++track;
                }
            }

            if(track < maxN) {
                maxN = track;
                resultMode = (float) annualSales.getData().get(i).getYValue();
            }
        }
        Text modeMsg = new Text("Mode");
        Text mode = new Text("£"+ String.format("%.2f", resultMode));

        modeMsg.setFont(new Font("Arial Bold", 24));
        mode.setFont(new Font("Arial", 22));

        VBox modeContainer = new VBox();
        modeContainer.getChildren().add(modeMsg);
        modeContainer.getChildren().add(mode);
        modeContainer.setSpacing(10);
        modeContainer.paddingProperty().setValue(new Insets(25));
        this.statisticsSummaryContainer.getChildren().add(modeContainer);

        this.container.getChildren().add(statisticsSummaryContainer);
    }


    @FXML
    public void refreshScreen() {
        //clear the graph container
        this.graphContainer.getChildren().clear();
        this.container.getChildren().remove(graphContainer);
        //clear text container
        this.statisticsSummaryContainer.getChildren().clear();
        this.container.getChildren().remove(statisticsSummaryContainer);
        //do the same as preparing
        initialize();
//        //prepare its x axis
//        List<String> xData = new ArrayList<>();
//
//        //populate with this year's orders
//        for(Order o : orderCollections.getPastOrders()) {
//            if(LocalDate.parse(o.getOrderDate().toString()).getYear() == LocalDate.now().getYear() && !xData.contains(o.getOrderDate().toString())) {
//                xData.add(o.getOrderDate().toString());
//            }
//        }
//
//
//        Collections.sort(xData);
//
//        Axis dateAxis = new CategoryAxis(FXCollections.observableArrayList(xData));
//        dateAxis.setLabel("Dates");
//
//        //prepare its y axis
//        Axis profit = new NumberAxis();
//        profit.setLabel("Sales profit gained in pounds.");
//
//        //prepare data
//        XYChart.Series<String, Number> annualSales = new XYChart.Series<>();
//        for(Order order : orderCollections.getPastOrders()) {
//            annualSales.getData().add(new XYChart.Data<>(order.getOrderDate().toString(), order.getProduct().getProductPrice()));
//        }
//
//        //create the graph
//        this.salesChart = new LineChart<String, Number>(dateAxis, profit);
//        this.salesChart.getData().add(annualSales);
//        annualSales.setName("Amount gained in sale");
//        this.salesChart.setTitle("Total no. of revenue gained in sales");
//        this.graphContainer.getChildren().add(salesChart);
//        this.container.getChildren().add(graphContainer);
//        //then add new graph and stat info to container.
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
            stage.setMaximized(false);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void btnExit() {
        this.container.getScene().getWindow().hide();
    }
}
