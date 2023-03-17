package com.manual;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class AboutController {
    @FXML
    private VBox container;

    @FXML
    public void onClose() {
        this.container.getScene().getWindow().hide();
    }
}
