package com.a360.fluentaqi.front.supervisor.historicaldata;

import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoricalDataController implements Initializable {
    @FXML
    private TableView<Feedback> txt_tableView;
    //主舞台
    public static Stage primaryStage;
    //当前登录成功的公众监督员用户身份
    public static Supervisor supervisor;

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

        TableColumn<Feedback, String> proviceNameColumn = new TableColumn<>("省区域");
        proviceNameColumn.setMinWidth(200);
        proviceNameColumn.setStyle("-fx-alignment: center;");	//居中
        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("provinceName"));

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

        TableColumn<Feedback, String> infoColumn = new TableColumn<>("反馈信息");
        infoColumn.setMinWidth(330);
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

        txt_tableView.getColumns().addAll(afIdColumn, proviceNameColumn,cityNameColumn,estimateGradeColumn,dateColumn,infoColumn);
        txt_tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Platform.runLater(() -> {
            double tableWidth = txt_tableView.getWidth();
            double totalPercent = 0.06 + 0.10 + 0.10 + 0.10 + 0.13 + 0.51; // 6列比例总和

            afIdColumn.setPrefWidth(tableWidth * 0.06 / totalPercent);
            proviceNameColumn.setPrefWidth(tableWidth * 0.10 / totalPercent);
            cityNameColumn.setPrefWidth(tableWidth * 0.10 / totalPercent);
            estimateGradeColumn.setPrefWidth(tableWidth * 0.10 / totalPercent);
            dateColumn.setPrefWidth(tableWidth * 0.13 / totalPercent);
            infoColumn.setPrefWidth(tableWidth * 0.51 / totalPercent);
        });
        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/";
        String filepath = ProPaht + "feedback.json";
        List<Feedback> afList = (List<Feedback>) JsonReader.readListFromJson(filepath, Feedback.class);
        System.out.println("afList"+afList);
        for(int i = afList.size()-1;i>=0 ;i--){			//按照时间排序,有近到远
            Feedback afb = afList.get(i);
            if(afb.getAfName().equals(supervisor.getRealName())){
                data.add(afb);
            }
        }
        txt_tableView.setItems(data);
    }

    public void getback(ActionEvent actionEvent) {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/supervisor/view.fxml", primaryStage,"环保公众监督平台-公众监督员端-AQI数据反馈");
    }
}
