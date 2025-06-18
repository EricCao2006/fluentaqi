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

import static com.a360.fluentaqi.LoginController.primaryStage;

public class LoginRunner extends Application {
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

    // 显示启动画面
    private void showSplashScreen(Stage stage) {
        Image splashImage = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/icons/logo.png"));
        ImageView imageView = new ImageView(splashImage);
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 300, 300);
        // 设置窗口无边框样式
        stage.initStyle(StageStyle.UNDECORATED);
        // 设置图标
        Image icon = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/icons/logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Fluent AQI");
        stage.setScene(scene);
        stage.show();
        // 1秒后跳转到登录界面
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
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
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/a360/fluentaqi/front/view.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 720, 540);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        // 设置图标
        Image icon = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/icons/logo.png"));
        primaryStage.getIcons().add(icon);
        stage.setTitle("Fluent AQI 登录");
        stage.setScene(scene);
        stage.setMinWidth(720);   // 最小宽度
        stage.setMinHeight(580);  // 最小高度（含标题栏）
        stage.show();
    }
}
