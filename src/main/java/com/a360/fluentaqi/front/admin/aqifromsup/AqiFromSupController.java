package com.a360.fluentaqi.front.admin.aqifromsup;


import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import com.a360.fluentaqi.front.admin.AdminController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AqiFromSupController implements Initializable {

    private static Stage primaryStage;
    public void getback(ActionEvent actionEvent) {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/admin/view.fxml",AdminController.primaryStage,"环保公众监督平台-管理端-主功能界面");

    }

    @FXML
    private TableView<Feedback> txt_tableView;

    public TableView<Feedback> getTxt_tableView() {
        return txt_tableView;
    }

    public void setTxt_tableView(TableView<Feedback> txt_tableView) {
        this.txt_tableView = txt_tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //初始化table 数据表
        TableColumn<Feedback, Integer> afIdColumn = new TableColumn<>("编号");
        afIdColumn.setMinWidth(200);
        afIdColumn.setStyle("-fx-alignment: center;");	//居中
        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));

        TableColumn<Feedback, String> provinceNameColumn = new TableColumn<>("省区域");
        provinceNameColumn.setMinWidth(200);
        provinceNameColumn.setStyle("-fx-alignment: center;");	//居中
        provinceNameColumn.setCellValueFactory(new PropertyValueFactory<>("provinceName"));

        TableColumn<Feedback, String> cityNameColumn = new TableColumn<>("市区域");
        cityNameColumn.setMinWidth(200);
        cityNameColumn.setStyle("-fx-alignment: center;");	//居中
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<Feedback, String> estimateGradeColumn = new TableColumn<>("预估等级");
        estimateGradeColumn.setMinWidth(200);
        estimateGradeColumn.setStyle("-fx-alignment: center;");	//居中
        estimateGradeColumn.setCellValueFactory(new PropertyValueFactory<>("estimateGrade"));

        TableColumn<Feedback, String> dateColumn = new TableColumn<>("反馈时间");
        dateColumn.setMinWidth(200);
        dateColumn.setStyle("-fx-alignment: center;");	//居中
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Feedback, String> afNameColumn = new TableColumn<>("反馈者");
        afNameColumn.setMinWidth(200);
        afNameColumn.setStyle("-fx-alignment: center;");	//居中
        afNameColumn.setCellValueFactory(new PropertyValueFactory<>("afName"));

        TableColumn<Feedback, String> infoColumn = new TableColumn<>("反馈信息");
        infoColumn.setMinWidth(210);
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

        txt_tableView.getColumns().addAll(afIdColumn, provinceNameColumn,cityNameColumn,estimateGradeColumn,dateColumn,afNameColumn,infoColumn);

        txt_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Platform.runLater(() -> {
            double tableWidth = txt_tableView.getWidth();
            double totalPercent = 0.10 + 0.15 + 0.15 + 0.10 + 0.20 + 0.15 + 0.15; // 7列比例总和

            afIdColumn.setPrefWidth(tableWidth * 0.10 / totalPercent);
            provinceNameColumn.setPrefWidth(tableWidth * 0.15 / totalPercent);
            cityNameColumn.setPrefWidth(tableWidth * 0.15 / totalPercent);
            estimateGradeColumn.setPrefWidth(tableWidth * 0.10 / totalPercent);
            dateColumn.setPrefWidth(tableWidth * 0.20 / totalPercent);
            afNameColumn.setPrefWidth(tableWidth * 0.15 / totalPercent);
            infoColumn.setPrefWidth(tableWidth * 0.15 / totalPercent);
        });

        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath = ProPaht + "aqi_feedback.json";
        List<Feedback> afList = (List<Feedback>) JsonReader.readListFromJson(filePath, Feedback.class);
        for(Feedback afb:afList){
            if(afb.getState().equals("未指派")){
                data.add(afb);
            }

        }
        txt_tableView.setItems(data);
    }
}
