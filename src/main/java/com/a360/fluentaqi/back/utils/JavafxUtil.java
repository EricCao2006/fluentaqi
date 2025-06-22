package com.a360.fluentaqi.back.utils;

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
     * @param primaryStage 舞台
     * @param title 标题栏
     * @param headerText 标题
     * @param contentText  内容
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
        dialogPane.getStyleClass().add("styled-dialog-pane");
        String themeCssPath = ThemeUtils.getIsDarkMode() ?
                "/com/a360/fluentaqi/front/css/dark.css" :
                "/com/a360/fluentaqi/front/css/light.css";

        dialogPane.getStylesheets().add(JavafxUtil.class.getResource(themeCssPath).toExternalForm());

        alert.showAndWait();

    }

    /**
     * 界面切换函数
     *
     * @param clazz 类本身
     * @param path 路径
     * @param primaryStage 舞台
     * @param title 标题
     * @return 舞台
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
            primaryStage.setWidth(720);
            primaryStage.setHeight(540);
            ThemeUtils.register(scene);
            // 获取控制器并注入 Stage
            Object controller = loader.getController();
            injectStageToController(controller, primaryStage); // 自定义方法注入 Stage
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
            ThemeUtils.register(scene);
            subStage.showAndWait();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return subStage;
    }

    // 新增私有方法：尝试向控制器注入 Stage
    private static void injectStageToController(Object controller, Stage stage) {
        if (controller == null || stage == null) return;

        try {
            // 尝试查找 setStage(Stage) 方法
            java.lang.reflect.Method method = controller.getClass().getMethod("setStage", Stage.class);
            method.invoke(controller, stage);
        } catch (Exception ignored) {
            // 如果没有该方法，忽略
        }
    }
}
