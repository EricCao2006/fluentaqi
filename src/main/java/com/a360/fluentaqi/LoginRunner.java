package com.a360.fluentaqi;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private void showSplashScreen(Stage stage) {
        Image splashImage = new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/icons/logo.png"));
        ImageView imageView = new ImageView(splashImage);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);

        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 200, 200);
        scene.setFill(Color.TRANSPARENT);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/a360/fluentaqi/front/icons/logo.png")));
        stage.setTitle("Fluent AQI");
        stage.setScene(scene);
        stage.show();

        DoubleProperty widthProperty = new SimpleDoubleProperty(stage.getWidth());
        DoubleProperty heightProperty = new SimpleDoubleProperty(stage.getHeight());

        widthProperty.addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) stage.setWidth(newVal.doubleValue());
        });

        heightProperty.addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0) stage.setHeight(newVal.doubleValue());
        });

        // 创建动画关键帧
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.8),
                        new KeyValue(imageView.scaleXProperty(), 1.2),
                        new KeyValue(imageView.scaleYProperty(), 1.2),
                        new KeyValue(widthProperty, 240),
                        new KeyValue(heightProperty, 240)
                )
        );

        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        timeline.setOnFinished(e -> {
            try {
                Stage loginStage = new Stage();
                loginStage.setTitle("Fluent AQI 登录");
                start(loginStage);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        timeline.play();
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
