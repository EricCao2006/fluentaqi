package com.a360.fluentaqi.front.gridder;

import com.a360.fluentaqi.LoginController;
import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.aqiabouts.LimitDto;
import com.a360.fluentaqi.back.aqiabouts.Limits;
import com.a360.fluentaqi.back.services.FeedbackService;
import com.a360.fluentaqi.back.services.impl.FeedbackServiceImpl;
import com.a360.fluentaqi.back.users.Gridder;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GridderController implements Initializable {

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
    private Label label_realName;

    @FXML
    private TextField txt_pm;

    @FXML
    private TextField txt_so2;

    @FXML
    private TableView<Feedback> txt_tableView;

    @FXML
    private Label label_so2explain;

    @FXML
    private Label label_coexplain;

    @FXML
    private Label label_pmexplain;

    @FXML
    private Label label_confirmexplain;

    private int so2level;
    private int colevel;
    private int pmlevel;

    public static Gridder gridMember;

    private FeedbackService aqiFeedbackService = new FeedbackServiceImpl();

    private LimitDto confirmDto;

    public TableView<Feedback> getTxt_tableView() {
        return txt_tableView;
    }

    public void setTxt_tableView(TableView<Feedback> txt_tableView) {
        this.txt_tableView = txt_tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 初始化网格员姓名
        label_realName.setText(gridMember.getRealName());
        //初始化table 数据表
        TableColumn<Feedback, Integer> afIdColumn = new TableColumn<>("编号");
        afIdColumn.setMinWidth(40);
        afIdColumn.setStyle("-fx-alignment: center;");	//居中
        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));

        TableColumn<Feedback, String> proviceNameColumn = new TableColumn<>("省区域");
        proviceNameColumn.setMinWidth(60);
        proviceNameColumn.setStyle("-fx-alignment: center;");	//居中
        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("provinceName"));

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

        TableColumn<Feedback, String> addressColumn = new TableColumn<>("具体地址");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Feedback, String> infoColumn = new TableColumn<>("反馈信息");
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

        txt_tableView.getColumns().addAll(afIdColumn,afNameColumn,dateColumn,estimateGradeColumn, proviceNameColumn,cityNameColumn,addressColumn,infoColumn);
        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath = ProPaht + "aqi_feedback.json";
        List<Feedback> afList = (List<Feedback>) JsonReader.readListFromJson(filePath , Feedback.class);
        for(Feedback afb:afList){
            if(afb.getGmName() != null && afb.getGmName().equals(gridMember.getRealName()) && afb.getState().equals("已指派")){
                data.add(afb);
            }
        }
        txt_tableView.setItems(data);
        //添加编号文本框事件监听
        txt_afId.focusedProperty().addListener((obs,wasFocused,isNowFocused)->{
            if(!isNowFocused){
                boolean flag = true;
                for(Feedback afb:afList){
                    if(afb.getGmName() != null && afb.getAfId().toString().equals(txt_afId.getText()) ){
                        flag = false;
                        return;
                    }
                }
                if(flag){
                    JavafxUtil.showAlert(primaryStage, "数据错误", "AQI反馈数据编号有误", "请重新输入AQI反馈数据编号","warn");
                    txt_afId.setText("");
                }
            }
        });
        txt_so2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                if(!txt_so2.getText().equals("")){
                    LimitDto dto = Limits.so2Limit(Double.parseDouble(txt_so2.getText()));
                    label_so2level.setText(dto.getLevel());
                    label_so2level.setStyle("-fx-text-fill:"+dto.getColor()+";");
                    label_so2explain.setText(dto.getExplain());
                    label_so2explain.setStyle("-fx-background-color:"+dto.getColor()+";");
                    so2level = dto.getIntlevel();
                    confirmDto = Limits.confirmLevel(so2level, colevel, pmlevel);
                    label_confirmlevel.setText(confirmDto.getLevel());
                    label_confirmlevel.setStyle("-fx-text-fill:"+confirmDto.getColor()+";");
                    label_confirmexplain.setText(confirmDto.getExplain());
                    label_confirmexplain.setStyle("-fx-background-color:"+confirmDto.getColor()+";");
                }
            }

        });

        txt_co.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                if(!txt_co.getText().equals("")){
                    LimitDto dto = Limits.coLimit(Double.parseDouble(txt_co.getText()));
                    label_colevel.setText(dto.getLevel());
                    label_colevel.setStyle("-fx-text-fill:"+dto.getColor()+";");
                    label_coexplain.setText(dto.getExplain());
                    label_coexplain.setStyle("-fx-background-color:"+dto.getColor()+";");
                    colevel = dto.getIntlevel();
                    confirmDto = Limits.confirmLevel(so2level, colevel, pmlevel);
                    label_confirmlevel.setText(confirmDto.getLevel());
                    label_confirmlevel.setStyle("-fx-text-fill:"+confirmDto.getColor()+";");
                    label_confirmexplain.setText(confirmDto.getExplain());
                    label_confirmexplain.setStyle("-fx-background-color:"+confirmDto.getColor()+";");
                }
            }

        });
        txt_pm.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                if(!txt_pm.getText().equals("")){
                    LimitDto dto = Limits.pmLimit(Double.parseDouble(txt_pm.getText()));
                    label_pmlevel.setText(dto.getLevel());
                    label_pmlevel.setStyle("-fx-text-fill:"+dto.getColor()+";");
                    label_pmexplain.setText(dto.getExplain());
                    label_pmexplain.setStyle("-fx-background-color:"+dto.getColor()+";");
                    pmlevel = dto.getIntlevel();
                    confirmDto = Limits.confirmLevel(so2level, colevel, pmlevel);
                    label_confirmlevel.setText(confirmDto.getLevel());
                    label_confirmlevel.setStyle("-fx-text-fill:"+confirmDto.getColor()+";");
                    label_confirmexplain.setText(confirmDto.getExplain());
                    label_confirmexplain.setStyle("-fx-background-color:"+confirmDto.getColor()+";");
                }
            }

        });

    }

    @FXML
    void confirmData(ActionEvent event) {
        Feedback afb = new Feedback();
        afb.setAfId(Integer.parseInt(txt_afId.getText()));
        afb.setState("已实测");
        afb.setSo2(Double.parseDouble(txt_so2.getText()));
        afb.setCo(Double.parseDouble(txt_co.getText()));
        afb.setPm(Double.parseDouble(txt_pm.getText()));
        afb.setConfirmDate(Limits.currentDate());
        afb.setConfirmLevel(confirmDto.getLevel());
        afb.setConfirmExplain(confirmDto.getExplain());
        afb.setGmName(gridMember.getRealName());
        aqiFeedbackService.confirmData(afb);
        JavafxUtil.showAlert(primaryStage, "提交成功", "污染物实测数据提交成功", "","info");
        //刷新页面数据表格
        ObservableList<Feedback> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath = ProPaht + "aqi_feedback.json";
        List<Feedback> aList = (List<Feedback>)JsonReader.readListFromJson(filePath, Feedback.class);
        for(Feedback a:aList){
            if(a.getGmName() != null && a.getGmName().equals(gridMember.getRealName()) && a.getState().equals("已指派")){
                data.add(a);
            }
        }
        txt_tableView.setItems(data);
        reset();
    }

    @FXML
    void getback(ActionEvent event) {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml", LoginController.primaryStage,"Fluent AQI 登录");
    }

    public void reset(){
        txt_afId.setText("");
        txt_so2.setText("");
        txt_co.setText("");
        txt_pm.setText("");
        label_so2explain.setText("");
        label_so2explain.setStyle("-fx-background-color:none;");
        label_coexplain.setText("");
        label_coexplain.setStyle("-fx-background-color:none;");
        label_pmexplain.setText("");
        label_pmexplain.setStyle("-fx-background-color:none;");
        label_confirmexplain.setText("");
        label_confirmexplain.setStyle("-fx-background-color:none;");
        label_so2level.setText("无");
        label_so2level.setStyle("-fx-text-fill:black;");
        label_colevel.setText("无");
        label_colevel.setStyle("-fx-text-fill:black;");
        label_pmlevel.setText("无");
        label_pmlevel.setStyle("-fx-text-fill:black;");
        label_confirmlevel.setText("无");
        label_confirmlevel.setStyle("-fx-text-fill:black;");
    }

}
