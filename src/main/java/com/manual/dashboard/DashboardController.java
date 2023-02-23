package com.manual.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DashboardController {
    @FXML
    private VBox btnStockLevelContainer;

    @FXML
    private Text txtPendingOrderMsg;

    @FXML
    private Text txtQuantityPendingOrder;

    @FXML
    private Button btnProcessOrder;

    @FXML
    private Text txtOrdersApprovedMsg;

    @FXML
    private Text txtOrdersApprovedQuantity;

    @FXML
    private Button btnViewPastOrders;

    @FXML
    private VBox stockLevelContainer;

    @FXML
    private Text txtStockLevelMsg;

    @FXML
    private Text txtStockLevelQuantity;

    @FXML
    private Button btnUpdateStock;

    @FXML
    private Text txtTotalStocks;
}
