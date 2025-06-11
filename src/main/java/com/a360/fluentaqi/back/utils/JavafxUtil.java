package com.a360.fluentaqi.back.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class JavafxUtil {
    /**
     * 配置各类型提示框
     * @param primaryStage
     * @param title
     * @param headerText
     * @param contentText
     */
    public static void showAlert(Stage primaryStage, String title, String headerText, String contentText, String alertType){
        Alert alert = null;
        switch (alertType) {
            case "warn":
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            case "info":
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            default:
                break;
        }
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * 界面切换函数
     * @param clazz
     * @param path
     * @param primaryStage
     * @param title
     */
    public static void showStage(Class clazz,String path,Stage primaryStage,String title){
        FXMLLoader loader = new FXMLLoader();
        System.out.println("path:"+path);
        URL url = clazz.getResource(path);
        loader.setLocation(url);
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 主界面切换子界面
     * @param clazz
     * @param path
     * @param primaryStage
     * @param title
     * @return
     */
    public static Stage showSubStage(Class clazz,String path,Stage primaryStage,String title){
        FXMLLoader loader = new FXMLLoader();
        URL url = clazz.getResource(path);
        loader.setLocation(url);
        Stage subStage = new Stage();
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            subStage.setTitle(title);
            subStage.setScene(scene);
            subStage.initOwner(primaryStage);
            subStage.initModality(Modality.WINDOW_MODAL);
            subStage.showAndWait();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return subStage;
    }
}
