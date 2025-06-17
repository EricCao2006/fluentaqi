package com.a360.fluentaqi;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginRunner1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 判断是否是启动画面阶段
        if (stage.getTitle() == null || stage.getTitle().isEmpty()) {
            showSplashScreen(stage);
        } else {
            showLoginScene(stage);
        }
    }

    // 显示启动画面（带圆角 Logo 和圆角窗口）
    private void showSplashScreen(Stage stage) {
        Image splashImage = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/logo.png"));
        ImageView imageView = new ImageView(splashImage);

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 200, 200);

        // 设置窗口无边框样式
        stage.initStyle(StageStyle.UNDECORATED); // 必须在 setTitle 和 show 之前调用

        // 设置图标
        Image icon = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/logo.png"));
        stage.getIcons().add(icon);

        stage.setTitle("Fluent AQI"); // 标记为启动阶段
        stage.setScene(scene);
        stage.show();

        // 2秒后跳转到登录界面
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> {
            try {
                Stage loginStage = new Stage();
                loginStage.setTitle("Fluent AQI 登录");
                start(loginStage); // 递归调用 start 方法加载登录界面
                stage.close(); // 关闭启动画面
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        delay.play();
    }

    // 显示登录界面
    private void showLoginScene(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/a360/fluentaqi/front/view.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 720, 540);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        // 设置图标
        Image icon = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Fluent AQI 登录");
        stage.setScene(scene);
        stage.show();
    }
}
