package com.a360.fluentaqi.back.utils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
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

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(JavafxUtil.class.getResource("/com/a360/fluentaqi/front/css/dark.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane"); // 应用 .dialog-pane 样式

        // ✅ 在 showAndWait 前设置尺寸即可
        alert.setResizable(false); // 非必须，但推荐禁用缩放

        // ✅ 先 show() 显示对话框
        alert.show();

        // ✅ 然后再 Platform.runLater 中获取 Stage 并设置尺寸
        Platform.runLater(() -> {
            Stage dialogStage = (Stage) dialogPane.getScene().getWindow();
            if (dialogStage != null) {
                dialogStage.setMinWidth(400);
                dialogStage.setMaxWidth(600);
                dialogStage.setMinHeight(250);
                dialogStage.setMaxHeight(400);
            }
        });
    }

    /**
     * 界面切换函数
     *
     * @param clazz
     * @param path
     * @param primaryStage
     * @param title
     * @return
     */
    public static Stage showStage(Class clazz, String path, Stage primaryStage, String title){
        FXMLLoader loader = new FXMLLoader();
        System.out.println("path:"+path);
        URL url = clazz.getResource(path);
        if (url == null) {
            System.out.println("wcnmlgb");
            throw new IllegalArgumentException("FXML file not found: " + path);
        }

        loader.setLocation(url);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryStage;
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
            Parent root = loader.load(); // 改为Parent
            Scene scene = new Scene(root);
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
