package com.a360.fluentaqi;

import com.a360.fluentaqi.front.admin.AdminController;
import com.a360.fluentaqi.front.gridder.GridderController;
import com.a360.fluentaqi.front.supervisor.SupervisorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setTitle("环保公共监督平台");
        stage.setScene(scene);
        stage.show();
    }
}

