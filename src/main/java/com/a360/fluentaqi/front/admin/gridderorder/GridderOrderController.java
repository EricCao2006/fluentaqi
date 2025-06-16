package com.a360.fluentaqi.front.admin.gridderorder;

import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GridderOrderController implements Initializable{

    public static Stage aqiInfoStage;

    @FXML
    private ChoiceBox<?> combo_realName;

    @FXML
    private TextField txt_afId;

    @FXML
    private TableView<Feedback> txt_tableView;

    @FXML
    void assignGridMember(ActionEvent event) {

    }

    @FXML
    void getback(ActionEvent event) {

    }

    public void initialize(URL location, ResourceBundle resources){


//        TableColumn<Feedback, Integer> afIdColumn = new TableColumn<>("编号");
//        afIdColumn.setMinWidth(40);
//        afIdColumn.setStyle("-fx-alignment: center;");	//居中
//        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));
//
//        TableColumn<Feedback, String> confirmDateColumn = new TableColumn<>("日期");
//        confirmDateColumn.setMinWidth(80);
//        confirmDateColumn.setStyle("-fx-alignment: center;");	//居中
//        confirmDateColumn.setCellValueFactory(new PropertyValueFactory<>("confirmDate"));
//
//        TableColumn<Feedback, String> proviceNameColumn = new TableColumn<>("省区域");
//        proviceNameColumn.setMinWidth(60);
//        proviceNameColumn.setStyle("-fx-alignment: center;");	//居中
//        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("proviceName"));
//
//        TableColumn<Feedback, String> cityNameColumn = new TableColumn<>("市区域");
//        cityNameColumn.setMinWidth(60);
//        cityNameColumn.setStyle("-fx-alignment: center;");	//居中
//        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));
//
//        TableColumn<Feedback, String> addressColumn = new TableColumn<>("市区域");
//        addressColumn.setMinWidth(60);
//        addressColumn.setStyle("-fx-alignment: center;");	//居中
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//
//        TableColumn<Feedback, String> estimateGradeColumn = new TableColumn<>("预估AQI");
//        estimateGradeColumn.setMinWidth(60);
//        estimateGradeColumn.setStyle("-fx-alignment: center;");	//居中
//        estimateGradeColumn.setCellValueFactory(new PropertyValueFactory<>("estimateGrade"));
//
//        TableColumn<Feedback, String> afNameColumn = new TableColumn<>("反馈者");
//        afNameColumn.setMinWidth(60);
//        afNameColumn.setStyle("-fx-alignment: center;");	//居中
//        afNameColumn.setCellValueFactory(new PropertyValueFactory<>("afName"));
//
//        TableColumn<Feedback, String> infoColumn = new TableColumn<>("反馈信息");
//        infoColumn.setMinWidth(210);
//        infoColumn.setCellValueFactory(new PropertyValueFactory<>("infomation"));
//
//        txt_tableView.getColumns().addAll(
//                afIdColumn, confirmDateColumn, proviceNameColumn, cityNameColumn,
//                addressColumn, estimateGradeColumn, afNameColumn, infoColumn
//        );
//        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
//        String filePath = ProPaht + "Aqi.json";
//        List<Feedback> alist = (List<Feedback>) JsonReader.readListFromJson(filePath, Feedback.class);
//        txt_tableView.getItems().setAll(alist);
    }


    @FXML
    void queryFeedback(ActionEvent event) {
        String afId = txt_afId.getText();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        String filePath = ProPaht + "Aqi.json";
        List<Feedback> alist = (List<Feedback>)JsonReader.readListFromJson(filePath, Feedback.class);
        boolean flag = true;
        for (Feedback af : alist) {
            if(af.getAfId().toString().equals(afId) && af.getState().equals("未指派")){

                flag = false;
                break;
            }

        }
        if(flag){
            JavafxUtil.showAlert(aqiInfoStage, "查询失败", "未找到当前编号反馈信息", "请重新输入AQI反馈数据编号","warn");

        }

    }

}

