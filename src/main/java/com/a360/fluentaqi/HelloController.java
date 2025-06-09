package com.a360.fluentaqi;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome!");

        // 创建一个定时器，在2秒后更改文本
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(2), event -> {
                    welcomeText.setText("Good Bye!");
                })
        );
        timeline.setCycleCount(1); // 设置只播放一次
        timeline.play(); // 开始播放时间线
    }
}
