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
        // 只注册一次鼠标点击关闭事件
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                hide();
            }
        });
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

        // 创建按钮并绑定事件
        Button themeBtn = createIconButton("/com/a360/fluentaqi/front/icons/theme.png", "切换主题");
        Button languageBtn = createIconButton("/com/a360/fluentaqi/front/icons/translate.png", "切换语言");

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
        setMouseTransparent(false);
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

    /**
     * 创建图标按钮
     *
     * @param iconPath     图标路径
     * @param tooltipText  提示文本
     * @return 图标按钮
     */
    private static Button createIconButton(String iconPath, String tooltipText) {
        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);

        Button button = new Button();
        button.getStyleClass().add("B-button");
        button.setMinSize(48, 48);
        button.setMaxSize(48, 48);
        button.setGraphic(imageView);

        return button;
    }

}
