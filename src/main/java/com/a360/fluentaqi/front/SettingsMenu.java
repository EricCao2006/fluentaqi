package com.a360.fluentaqi.front;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.a360.fluentaqi.back.utils.ThemeUtils.changeTheme;

public class SettingsMenu extends VBox {

    private static SettingsMenu instance;

    private SettingsMenu() {
        super(10);
        getStyleClass().add("floating-menu");
        setMouseTransparent(false);
        setVisible(false);
    }

    public static SettingsMenu getInstance() {
        if (instance == null) {
            instance = new SettingsMenu();
        }
        return instance;
    }

    /**
     * 显示菜单
     *
     * @param anchorNode 锚点控件（如设置按钮）
     * @param stage      当前窗口
     */
    public void show(Node anchorNode, Stage stage) {

        // 清空旧内容
        getChildren().clear();

        // 创建按钮
        Button darkModeBtn = new Button("🌙");
        Button LanguageBtn = new Button("🎨");

        darkModeBtn.getStyleClass().add("B-button");
        LanguageBtn.getStyleClass().add("B-button");

        darkModeBtn.setMinSize(48, 48);
        darkModeBtn.setMaxSize(48, 48);

        LanguageBtn.setMinSize(48, 48);
        LanguageBtn.setMaxSize(48, 48);

        darkModeBtn.setOnAction(e -> {
            changeTheme();
            hide();
        });

        LanguageBtn.setOnAction(e -> {
            //TODO: 语言切换
            hide();
        });

        getChildren().addAll(darkModeBtn, LanguageBtn);

        // 添加到场景中
        AnchorPane root = (AnchorPane) stage.getScene().getRoot();
        if (!root.getChildren().contains(this)) {
            root.getChildren().add(this);
        }

        // 定位
        double x = anchorNode.localToScene(0, 0).getX();
        double y = anchorNode.localToScene(0, 0).getY() + anchorNode.getBoundsInParent().getHeight();
        setLayoutX(x);
        setLayoutY(y);

        setVisible(true);

        // 点击外部关闭
        stage.getScene().setOnMouseClicked(e -> {
            if (!localToScene(getBoundsInLocal()).contains(e.getX(), e.getY())) {
                hide();
            }
        });
    }

    /**
     * 隐藏菜单
     */
    public void hide() {
        setVisible(false);
        setMouseTransparent(true);
        ((AnchorPane) getParent()).getChildren().remove(this);
    }
}
