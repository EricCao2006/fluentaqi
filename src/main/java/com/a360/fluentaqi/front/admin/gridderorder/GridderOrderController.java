package com.a360.fluentaqi.front.admin.gridderorder;

import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.services.FeedbackService;
import com.a360.fluentaqi.back.services.impl.FeedbackServiceImpl;
import com.a360.fluentaqi.back.users.Gridder;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.back.utils.JsonReader;
import com.a360.fluentaqi.front.admin.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ChoiceBox<String> combo_realName;

    @FXML
    private TextField txt_afId;

    @FXML
    private TableView<Feedback> txt_tableView;

    private FeedbackService aqiFeedbackService = new FeedbackServiceImpl();
    @FXML
    void assignGridMember(ActionEvent event) {
        Feedback selectedFeedback = txt_tableView.getSelectionModel().getSelectedItem();

        if(combo_realName.getValue().equals("请选择网格员")){
            JavafxUtil.showAlert(aqiInfoStage, "指派失败", "您没有选择要指派的网格员", "请选择您要指派的网格员","warn");
            return;
        }
        // 获取反馈ID
        String afId = String.valueOf(selectedFeedback.getAfId());

        // 执行指派操作
        aqiFeedbackService.assignGridder(afId, combo_realName.getValue());

        // 显示成功消息
        JavafxUtil.showAlert(aqiInfoStage, "指派成功", "AQI反馈信息指派成功!",
                "反馈已指派给 " + combo_realName.getValue(), "info");

        // 重置界面
        initConroller();
    }

    @FXML
    void getback(ActionEvent event) {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/admin/view.fxml", AdminController.primaryStage,"管理员端-主功能界面");
    }
    //初始化,fxml运行时自动运行initialize
    public void initialize(URL location, ResourceBundle resources){
        //初始化表格
        initTableColumns();
        //标签初始化
        initConroller();
        //初始化网格员
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/";
        String filePath = ProPaht + "grid_member.json";
        List<Gridder> glist = (List<Gridder>) JsonReader.readListFromJson(filePath, Gridder.class);
        for (Gridder gm : glist) {
            if(gm.getState().equals("工作中")){
                combo_realName.getItems().add(gm.getRealName());
            }
        }
    }


    private void initTableColumns() {
        // 清除现有列
        txt_tableView.getColumns().clear();

        TableColumn<Feedback, Integer> afIdColumn = new TableColumn<>("编号");
        afIdColumn.setMinWidth(40);
        afIdColumn.setStyle("-fx-alignment: center;");
        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));

        TableColumn<Feedback, String> confirmDateColumn = new TableColumn<>("日期");
        confirmDateColumn.setMinWidth(80);
        confirmDateColumn.setStyle("-fx-alignment: center;");
        confirmDateColumn.setCellValueFactory(new PropertyValueFactory<>("confirmDate"));

        TableColumn<Feedback, String> proviceNameColumn = new TableColumn<>("省区域");
        proviceNameColumn.setMinWidth(60);
        proviceNameColumn.setStyle("-fx-alignment: center;");
        proviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("proviceName"));

        TableColumn<Feedback, String> cityNameColumn = new TableColumn<>("市区域");
        cityNameColumn.setMinWidth(60);
        cityNameColumn.setStyle("-fx-alignment: center;");
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<Feedback, String> addressColumn = new TableColumn<>("详细地址");
        addressColumn.setMinWidth(100);
        addressColumn.setStyle("-fx-alignment: center;");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Feedback, String> estimateGradeColumn = new TableColumn<>("预估AQI");
        estimateGradeColumn.setMinWidth(60);
        estimateGradeColumn.setStyle("-fx-alignment: center;");
        estimateGradeColumn.setCellValueFactory(new PropertyValueFactory<>("estimateGrade"));

        TableColumn<Feedback, String> afNameColumn = new TableColumn<>("反馈者");
        afNameColumn.setMinWidth(60);
        afNameColumn.setStyle("-fx-alignment: center;");
        afNameColumn.setCellValueFactory(new PropertyValueFactory<>("afName"));

        TableColumn<Feedback, String> infoColumn = new TableColumn<>("反馈信息");
        infoColumn.setMinWidth(210);
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("infomation"));

        txt_tableView.getColumns().addAll(
                afIdColumn, confirmDateColumn, proviceNameColumn, cityNameColumn,
                addressColumn, estimateGradeColumn, afNameColumn, infoColumn);
    }

    @FXML
    void queryFeedback(ActionEvent event) {
        String afId = txt_afId.getText();
        // 修正文件路径为正确的反馈数据路径
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/";
        String filePath = ProPaht + "Aqi.json";
        List<Feedback> alist = (List<Feedback>) JsonReader.readListFromJson(filePath, Feedback.class);

        boolean found = false;
        ObservableList<Feedback> tableData = FXCollections.observableArrayList();

        for (Feedback af : alist) {
            if (af.getAfId().toString().equals(afId) && "未指派".equals(af.getState())) {
                // 添加到表格数据
                tableData.add(af);
                found = true;
                break; // 只显示一条匹配记录
            }
        }

        txt_tableView.setItems(tableData);

        if (!found) {
            JavafxUtil.showAlert(aqiInfoStage, "查询失败", "未找到当前编号反馈信息",
                    "请重新输入有效的AQI反馈数据编号", "warn");
            // 清空表格
            txt_tableView.getItems().clear();
        }
        }


    public void initConroller(){
        txt_afId.setText("");
        combo_realName.setValue("请选择网格员");
        // 清空表格
        txt_tableView.getItems().clear();
    }

}













