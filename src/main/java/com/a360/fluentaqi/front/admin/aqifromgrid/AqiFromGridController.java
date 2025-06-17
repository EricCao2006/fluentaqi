package com.a360.fluentaqi.front.admin.aqifromgrid;


import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.data.AqiFeedbackData;
import com.a360.fluentaqi.back.users.Gridder;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import com.a360.fluentaqi.front.admin.AdminController;
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
        proviceNameColumn.setMinWidth(60);
        proviceNameColumn.setStyle("-fx-alignment: center;");	//居中
        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("proviceName"));

        TableColumn<Feedback, String> cityNameColumn = new TableColumn<>("市区域");
        cityNameColumn.setMinWidth(60);
        cityNameColumn.setStyle("-fx-alignment: center;");	//居中
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<Feedback, String> estimateGradeColumn = new TableColumn<>("预估等级");
        estimateGradeColumn.setMinWidth(60);
        estimateGradeColumn.setStyle("-fx-alignment: center;");	//居中
        estimateGradeColumn.setCellValueFactory(new PropertyValueFactory<>("estimateGrade"));

        TableColumn<Feedback, String> dateColumn = new TableColumn<>("反馈时间");
        dateColumn.setMinWidth(80);
        dateColumn.setStyle("-fx-alignment: center;");	//居中
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Feedback, String> afNameColumn = new TableColumn<>("反馈者");
        afNameColumn.setMinWidth(60);
        afNameColumn.setStyle("-fx-alignment: center;");	//居中
        afNameColumn.setCellValueFactory(new PropertyValueFactory<>("afName"));

        TableColumn<Feedback, String> so2Column = new TableColumn<>("SQ2浓度(ug/m3)");
        so2Column.setMinWidth(80);
        so2Column.setStyle("-fx-alignment: center;");	//居中
        so2Column.setCellValueFactory(new PropertyValueFactory<>("so2"));

        TableColumn<Feedback, String> coColumn = new TableColumn<>("CO浓度(ug/m3)");
        coColumn.setMinWidth(80);
        coColumn.setStyle("-fx-alignment: center;");	//居中
        coColumn.setCellValueFactory(new PropertyValueFactory<>("co"));

        TableColumn<Feedback, String> pmColumn = new TableColumn<>("PM2.5浓度(ug/m3)");
        pmColumn.setMinWidth(80);
        pmColumn.setStyle("-fx-alignment: center;");	//居中
        pmColumn.setCellValueFactory(new PropertyValueFactory<>("pm"));

        TableColumn<Feedback, String> confirmLevelColumn = new TableColumn<>("实测等级");
        confirmLevelColumn.setMinWidth(60);
        confirmLevelColumn.setStyle("-fx-alignment: center;");	//居中
        confirmLevelColumn.setCellValueFactory(new PropertyValueFactory<>("confirmLevel"));

        TableColumn<Feedback, String> confirmExplainColumn = new TableColumn<>("等级说明");
        confirmExplainColumn.setMinWidth(60);
        confirmExplainColumn.setStyle("-fx-alignment: center;");	//居中
        confirmExplainColumn.setCellValueFactory(new PropertyValueFactory<>("confirmExplain"));

        TableColumn<Feedback, String> confirmDateColumn = new TableColumn<>("实测日期");
        confirmDateColumn.setMinWidth(80);
        confirmDateColumn.setStyle("-fx-alignment: center;");	//居中
        confirmDateColumn.setCellValueFactory(new PropertyValueFactory<>("confirmDate"));

        TableColumn<Feedback, String> gmNameColumn = new TableColumn<>("网格员");
        gmNameColumn.setMinWidth(60);
        gmNameColumn.setStyle("-fx-alignment: center;");	//居中
        gmNameColumn.setCellValueFactory(new PropertyValueFactory<>("gmName"));

        txt_tableView.getColumns().addAll(afIdColumn, proviceNameColumn,cityNameColumn,estimateGradeColumn,dateColumn,afNameColumn,so2Column,coColumn,pmColumn,confirmLevelColumn,confirmExplainColumn,confirmDateColumn,gmNameColumn);
        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/";
        String filePath = ProPaht + "Feedback.json"; // 添加文件路径
        List<Feedback> afList = (List<Feedback>) JsonReader.readListFromJson(filePath, Feedback.class);
        for(Feedback afb:afList){
            if(afb.getState().equals("已实测")){
                data.add(afb);
            }
        }
        txt_tableView.setItems(data);
    }
}
