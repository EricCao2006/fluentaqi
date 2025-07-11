package com.a360.fluentaqi.front.admin.aqifromgrid;


import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.users.Gridder;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import com.a360.fluentaqi.front.SettingsMenu;
import com.a360.fluentaqi.front.admin.AdminController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AqiFromGridController implements Initializable {
    public static Gridder gridMember;

    @FXML
    private TableView<Feedback> txt_tableView;

    private static Stage primaryStage;

    public void getback(ActionEvent actionEvent) {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/admin/view.fxml",AdminController.primaryStage,"环保公众监督平台-管理端-主功能界面");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Feedback, Integer> afIdColumn = new TableColumn<>("编号");
        afIdColumn.setMinWidth(40);
        afIdColumn.setStyle("-fx-alignment: center;");	//居中
        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));

        TableColumn<Feedback, String> proviceNameColumn = new TableColumn<>("省区域");
        proviceNameColumn.setMinWidth(80);
        proviceNameColumn.setStyle("-fx-alignment: center;");	//居中
        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("provinceName"));

        TableColumn<Feedback, String> cityNameColumn = new TableColumn<>("市区域");
        cityNameColumn.setMinWidth(200);
        cityNameColumn.setStyle("-fx-alignment: center;");	//居中
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<Feedback, String> estimateGradeColumn = new TableColumn<>("预估等级");
        estimateGradeColumn.setMinWidth(500);
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

        TableColumn<Feedback, String> so2Column = new TableColumn<>("SQ2浓度(ug/m3)");
        so2Column.setMinWidth(150);
        so2Column.setStyle("-fx-alignment: center;");	//居中
        so2Column.setCellValueFactory(new PropertyValueFactory<>("so2"));

        TableColumn<Feedback, String> coColumn = new TableColumn<>("CO浓度(ug/m3)");
        coColumn.setMinWidth(150);
        coColumn.setStyle("-fx-alignment: center;");	//居中
        coColumn.setCellValueFactory(new PropertyValueFactory<>("co"));

        TableColumn<Feedback, String> pmColumn = new TableColumn<>("PM2.5浓度(ug/m3)");
        pmColumn.setMinWidth(150);
        pmColumn.setStyle("-fx-alignment: center;");	//居中
        pmColumn.setCellValueFactory(new PropertyValueFactory<>("pm"));

        TableColumn<Feedback, String> confirmLevelColumn = new TableColumn<>("实测等级");
        confirmLevelColumn.setMinWidth(500);

        confirmLevelColumn.setStyle("-fx-alignment: center;");	//居中
        confirmLevelColumn.setCellValueFactory(new PropertyValueFactory<>("confirmLevel"));

        TableColumn<Feedback, String> confirmExplainColumn = new TableColumn<>("等级说明");
        confirmExplainColumn.setMinWidth(1000);
        confirmExplainColumn.setStyle("-fx-alignment: center;");	//居中
        confirmExplainColumn.setCellValueFactory(new PropertyValueFactory<>("confirmExplain"));

        TableColumn<Feedback, String> confirmDateColumn = new TableColumn<>("实测日期");
        confirmDateColumn.setMinWidth(200);
        confirmDateColumn.setStyle("-fx-alignment: center;");	//居中
        confirmDateColumn.setCellValueFactory(new PropertyValueFactory<>("confirmDate"));

        TableColumn<Feedback, String> gmNameColumn = new TableColumn<>("网格员");
        gmNameColumn.setMinWidth(150);
        gmNameColumn.setStyle("-fx-alignment: center;");	//居中
        gmNameColumn.setCellValueFactory(new PropertyValueFactory<>("gmName"));

        txt_tableView.getColumns().addAll(afIdColumn, proviceNameColumn,cityNameColumn,estimateGradeColumn,dateColumn,afNameColumn,so2Column,coColumn,pmColumn,confirmLevelColumn,confirmExplainColumn,confirmDateColumn,gmNameColumn);
        txt_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Platform.runLater(() -> {
            double tableWidth = txt_tableView.getWidth();
            double totalPercent = 0.03125 + 0.046875 + 0.046875 + 0.0625 + 0.0625 + 0.046875 + 0.15625 + 0.15625 + 0.15625 + 0.0625 + 0.0625 + 0.0625 + 0.046875; // 13列比例总和

            afIdColumn.setPrefWidth(tableWidth * 0.03125 / totalPercent);
            proviceNameColumn.setPrefWidth(tableWidth * 0.046875 / totalPercent);
            cityNameColumn.setPrefWidth(tableWidth * 0.046875 / totalPercent);
            estimateGradeColumn.setPrefWidth(tableWidth * 0.0625 / totalPercent);
            dateColumn.setPrefWidth(tableWidth * 0.0625 / totalPercent);
            afNameColumn.setPrefWidth(tableWidth * 0.046875 / totalPercent);
            so2Column.setPrefWidth(tableWidth * 0.15625 / totalPercent);
            coColumn.setPrefWidth(tableWidth * 0.15625 / totalPercent);
            pmColumn.setPrefWidth(tableWidth * 0.15625 / totalPercent);
            confirmLevelColumn.setPrefWidth(tableWidth * 0.0625 / totalPercent);
            confirmExplainColumn.setPrefWidth(tableWidth * 0.0625 / totalPercent);
            confirmDateColumn.setPrefWidth(tableWidth * 0.0625 / totalPercent);
            gmNameColumn.setPrefWidth(tableWidth * 0.046875 / totalPercent);
        });
        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath = ProPaht + "aqi_feedback.json"; // 添加文件路径
        List<Feedback> afList = (List<Feedback>) JsonReader.readListFromJson(filePath, Feedback.class);
        for(Feedback afb:afList){
            if(afb.getState().equals("已实测")){
                data.add(afb);
            }
        }
        txt_tableView.setItems(data);
    }

    @FXML
    private Button btnSettings;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void showSettingsMenu() {
        SettingsMenu menu = SettingsMenu.getInstance();
        menu.show(btnSettings, stage);
    }
}
