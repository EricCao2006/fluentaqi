package com.a360.fluentaqi.front.admin;

import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.front.admin.gridderorder.GridderOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController  {

    public static Stage primaryStage;

    private ImageView txt_imageView;

    public ImageView getTxt_imageView() {
        return txt_imageView;
    }

    public void setTxt_imageView(ImageView txt_imageView) {
        this.txt_imageView = txt_imageView;
    }

//    public void initialize(URL location, ResourceBundle resources) {
//        //初始化图片
//        Image image = new Image("");
//        txt_imageView.setImage(image);
//        txt_imageView.setPreserveRatio(false);   //禁用保持纵横比
//    }

    @FXML
    void aqiAssign(){
        GridderOrderController.aqiInfoStage = JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/admin/gridderorder/view.fxml", primaryStage, "环保公众监督平台-管理端-AQI反馈数据指派");
    }

    @FXML
    void aqiConfirm(){
        JavafxUtil.showStage(this.getClass(), "/com/a360/fluentaqi/front/admin/aqifromgrid/view.fxml", primaryStage, "环保公众监督平台-管理端-AQI实测数据查询");
    }

    @FXML
    void aqiInfo() {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/admin/aqifromsup/view.fxml",primaryStage,"环保公众监督平台-管理端-AQI反馈数据查询");
    }

    @FXML
    void getback() {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml",primaryStage,"登录");
    }

}

