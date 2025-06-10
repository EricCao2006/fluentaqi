package com.a360.fluentaqi;

import com.pixelduke.transit.TransitTheme;
import com.pixelduke.transit.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginRunner extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/a360/fluentaqi/front/view.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root, 720, 540);

        // ✅ 应用 Transit 主题
        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
        transitTheme.setScene(scene);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

        stage.setTitle("环保公共监督平台");
        stage.setScene(scene);
        stage.show();
    }
}

