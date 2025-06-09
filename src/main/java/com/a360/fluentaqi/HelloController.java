package com.a360.fluentaqi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onButtonClick1() {
        welcomeText.setText("啊哈！");
    }
    @FXML
    protected void onButtonClick2() {
        welcomeText.setText("歪比哇卜！");
    }
}
