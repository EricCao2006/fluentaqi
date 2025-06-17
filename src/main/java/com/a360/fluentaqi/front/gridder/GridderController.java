package com.a360.fluentaqi.front.gridder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GridderController {

    public static Stage primaryStage;

    @FXML
    private Label label_colevel;

    @FXML
    private Label label_confirmlevel;

    @FXML
    private Label label_pmlevel;

    @FXML
    private Label label_so2level;

    @FXML
    private TextField txt_afId;

    @FXML
    private TextField txt_co;

    @FXML
    private Label txt_name;

    @FXML
    private TextField txt_pm;

    @FXML
    private TextField txt_so2;

    @FXML
    private TableView<?> txt_tableView;

    @FXML
    void confirmData(ActionEvent event) {

    }

    @FXML
    void getback(ActionEvent event) {

    }

}
