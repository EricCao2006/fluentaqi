package com.a360.fluentaqi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.a360.fluentaqi.LoginController.primaryStage;

public class LoginRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 设置LoginController的primaryStage
        LoginController.primaryStage = stage; // 新增这行

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/a360/fluentaqi/front/view.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 720, 540);

        Image icon = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/logo.png"));
        primaryStage.getIcons().add(icon);
        stage.setTitle("Fluent AQI 登录");
        stage.setScene(scene);
        stage.show();
    }
}

