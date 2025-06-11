package com.a360.fluentaqi.front.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public static Stage primaryStage;

    private ImageView txt_imageView;

    public ImageView getTxt_imageView() {
        return txt_imageView;
    }

    public void setTxt_imageView(ImageView txt_imageView) {
        this.txt_imageView = txt_imageView;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //初始化图片
        Image image = new Image("");
        txt_imageView.setImage(image);
        txt_imageView.setPreserveRatio(false);   //禁用保持纵横比
    }

    @FXML
    void aqiAssign(ActionEvent event){

    }

    @FXML
    void aqiConfirm(ActionEvent event){

    }

    @FXML
    void aqiInfo(ActionEvent event) {

    }

}

