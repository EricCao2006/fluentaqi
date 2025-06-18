package com.a360.fluentaqi.front.supervisor;

import com.a360.fluentaqi.back.aqiabouts.Aqi;
import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.aqiabouts.Province;
import com.a360.fluentaqi.back.services.FeedbackService;
import com.a360.fluentaqi.back.services.impl.FeedbackServiceImpl;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.back.utils.CommonUtil;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import com.a360.fluentaqi.front.supervisor.historicaldata.HistoricalDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class SupervisorController implements Initializable {
    public static Stage primaryStage;

    @FXML
    void historicalData() {
        JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/supervisor/historicaldata/view.fxml", primaryStage, "监督员端-AQI反馈历史记录");
    }

    void register() {
        JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/supervisor/register/view.fxml", primaryStage, "监督员端-注册页面");
    }

    @FXML
    void getback() {
        JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/view.fxml", primaryStage, "Fluent AQI 登录");
    }

    //    @FXML
//    private TableView<Aqi> txt_tableView;
    @FXML
    private ChoiceBox<String> txt_province;
    @FXML
    private ChoiceBox<String> txt_city;
    @FXML
    private TextField txt_address;
    @FXML
    private ChoiceBox<String> txt_level;
    @FXML
    private TextField txt_information;
    @FXML
    private Label label_realName;
    public static Supervisor supervisor;
    private FeedbackService aqiFeedbackService = new FeedbackServiceImpl();

    //    public TableView<Aqi> getTxt_tableView() {
//        return txt_tableView;
//    }
//    public void setTxt_tableView(TableView<Aqi> txt_tableView) {
//        this.txt_tableView = txt_tableView;
//    }
    public ChoiceBox<String> getTxt_province() {
        return txt_province;
    }

    public void setTxt_province(ChoiceBox<String> txt_province) {
        this.txt_province = txt_province;
    }

    public ChoiceBox<String> getTxt_city() {
        return txt_city;
    }

    public void setTxt_city(ChoiceBox<String> txt_city) {
        this.txt_city = txt_city;
    }

    public TextField getTxt_address() {
        return txt_address;
    }

    public void setTxt_address(TextField txt_address) {
        this.txt_address = txt_address;
    }

    public ChoiceBox<String> getTxt_level() {
        return txt_level;
    }

    public void setTxt_level(ChoiceBox<String> txt_level) {
        this.txt_level = txt_level;
    }

    public TextField getTxt_information() {
        return txt_information;
    }

    public void setTxt_information(TextField txt_information) {
        this.txt_information = txt_information;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (supervisor != null) {
            label_realName.setText(supervisor.getRealName());
        }
        //初始化表格
//        TableColumn<Aqi, String> levelColumn = new TableColumn<>("级别");
//        levelColumn.setMinWidth(80);
//        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
//
//        TableColumn<Aqi, String> explainColumn = new TableColumn<>("说明");
//        explainColumn.setMinWidth(80);
//        explainColumn.setCellValueFactory(new PropertyValueFactory<>("explain"));
//
//        TableColumn<Aqi, String> impactColumn = new TableColumn<>("对健康影响");
//        impactColumn.setMinWidth(425);
//        impactColumn.setCellValueFactory(new PropertyValueFactory<>("impact"));
//
////        txt_tableView.getColumns().addAll(levelColumn, explainColumn,impactColumn);
//        ObservableList<Aqi> data = FXCollections.observableArrayList();
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath1 = ProPaht + "aqi.json"; // 添加文件路径
        List<Aqi> afList = (List<Aqi>) JsonReader.readListFromJson(filePath1, Aqi.class);
//        for (Aqi aqi : afList) {
//            data.add(aqi);
//        }
//        txt_tableView.setItems(data);
        //初始化AQI等级下拉列表
        for (Aqi aqi : afList) {
            txt_level.getItems().add(aqi.getLevel());
        }
        txt_level.setValue("预估AQI等级");

        //初始化省市区域
        String filePath2 = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/Province.json";
        List<Province> PList = (List<Province>) JsonReader.readListFromJson(filePath2, Province.class);
        for (Province province : PList) {
            txt_province.getItems().add(province.getProvinceName());
        }
        txt_province.setValue("请选择省区域");
        txt_city.setValue("请选择市区域");
        txt_province.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txt_city.setItems(FXCollections.observableArrayList());    //先清空
                List<String> clist = new ArrayList<String>();
                for (int i = 0; i < PList.size(); i++) {
                    if (newValue.equals(PList.get(i).getProvinceName())) {
                        clist = PList.get(i).getCityName();
                    }
                }
                for (String cityName : clist) {
                    txt_city.getItems().add(cityName);
                }
                txt_city.setValue("请选择市区域");
            }

        });


    }

    public void saveFeedBack() {
        Feedback afb = new Feedback();
        afb.setAddress(txt_address.getText());
        afb.setAfName(supervisor.getRealName());
        afb.setProvinceName(txt_province.getValue());
        afb.setCityName(txt_city.getValue());
        afb.setEstimateGrade(txt_level.getValue());
        afb.setInformation(txt_information.getText());
        afb.setDate(CommonUtil.currentDate());
        afb.setState("未指派");
        aqiFeedbackService.saveFeedBack(afb);
        JavafxUtil.showAlert(primaryStage, "反馈信息成功", "您的预估AQI信息提交成功", "感谢您的反馈!", "info");
        HistoricalDataController.primaryStage = primaryStage;
        JavafxUtil.showStage(this.getClass(), "src/main/resources/com/a360/fluentaqi/front/supervisor/historicaldata/view.fxml", primaryStage, "监督员端-AQI历史反馈记录");

    }

    public void feedBackList() {
        HistoricalDataController.primaryStage = primaryStage;
        JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/supervisor/historicaldata/view.fxml", primaryStage, "监督员端-AQI历史反馈记录");
    }
}
