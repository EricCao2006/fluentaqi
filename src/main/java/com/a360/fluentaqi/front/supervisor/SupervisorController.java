package com.a360.fluentaqi.front.supervisor;

import com.a360.fluentaqi.back.utils.JavafxUtil;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SupervisorController {
    public static Stage primaryStage;
    @FXML
    void historicalData(){
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/supervisor/historicaldata/view.fxml",primaryStage,"环保公众监督平台-监督员端-AQI反馈历史记录");
    }
    void register(){
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/supervisor/register/view.fxml",primaryStage,"环保公众监督平台-监督员端-注册页面");
    }
    @FXML
    void getback() {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml", primaryStage,"Fluent AQI 登录");
    }
}
