package com.a360.fluentaqi.back.utils;

import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

/**
 * 主题控制器
 * @author 曹博宇@A360
 */
public class ThemeController {
    private static ThemeController instance;
    private boolean isDarkMode = false;
    private final List<Scene> observedScenes = new ArrayList<>();

    private ThemeController() {}

    public static ThemeController getInstance() {
        if (instance == null) {
            instance = new ThemeController();
        }
        return instance;
    }

    // 注册一个 Scene 以便后续更新其样式
    public void registerScene(Scene scene) {
        observedScenes.add(scene);
        applyCurrentThemeToScene(scene);
    }

    // 切换主题
    public void toggleTheme() {
        isDarkMode = !isDarkMode;
        for (Scene scene : observedScenes) {
            applyCurrentThemeToScene(scene);
        }
    }

    // 获取当前主题路径
    public String getCurrentStylesheetPath() {
        return isDarkMode ?
                "/com/a360/fluentaqi/front/css/dark.css" :
                "/com/a360/fluentaqi/front/css/light.css";
    }

    // 应用当前主题到指定的 Scene
    private void applyCurrentThemeToScene(Scene scene) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(getCurrentStylesheetPath()).toExternalForm());
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }
}
