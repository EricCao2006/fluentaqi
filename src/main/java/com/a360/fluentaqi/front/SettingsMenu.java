package com.a360.fluentaqi.front;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.a360.fluentaqi.back.utils.ThemeUtils.changeTheme;

public class SettingsMenu extends HBox {

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

        // 如果已经显示，则收起菜单
        if (getChildren().size() > 0 && isVisible()) {
            hide();
            return;
        }

        // 清空旧内容
        getChildren().clear();

        // 创建“主题”按钮
        Button themeBtn = new Button();
        ImageView themeView = new ImageView(new Image("/com/a360/fluentaqi/front/icons/theme.png"));
        themeView.setFitWidth(24);
        themeView.setFitHeight(24);
        themeBtn.setGraphic(themeView);
        themeBtn.getStyleClass().add("B-button");

        // 创建“语言切换”按钮
        Button languageBtn = new Button();
        ImageView languageView = new ImageView(new Image("/com/a360/fluentaqi/front/icons/translate.png"));
        languageView.setFitWidth(24);
        languageView.setFitHeight(24);
        languageBtn.setGraphic(languageView);
        languageBtn.getStyleClass().add("B-button");

        themeBtn.setOnAction(e -> {
            changeTheme();
            hide();
        });

        languageBtn.setOnAction(e -> {
            //TODO: 语言切换
            hide();
        });

        getChildren().addAll(themeBtn, languageBtn);

        // 添加到场景中
        Pane root = (Pane) stage.getScene().getRoot();
        if (!root.getChildren().contains(this)) {
            root.getChildren().add(this);
        }

        // 定位
        double x = anchorNode.localToScene(0, 0).getX();
        double y = anchorNode.localToScene(0, 0).getY() - getHeight();
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
        // 只有当前组件在父布局中才执行 remove
        if (getParent() instanceof Pane root) {
            root.getChildren().remove(this);
        }
    }
}
