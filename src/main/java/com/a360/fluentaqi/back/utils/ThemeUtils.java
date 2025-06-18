package com.a360.fluentaqi.back.utils;

import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

/**
 * 主题切换工具类（单文件极简实现）
 */
public class ThemeUtils {
    private static boolean isDarkMode = false;
    private static final List<Scene> scenes = new ArrayList<>();

    /**
     * 注册 Scene 以支持主题切换
     */
    public static void register(Scene scene) {
        scenes.add(scene);
        applyThemeToScene(scene);
    }

    /**
     * 切换为浅色模式
     */
    public static void setLight() {
        isDarkMode = false;
        applyThemeToAllScenes();
    }

    /**
     * 切换为深色模式
     */
    public static void setDark() {
        isDarkMode = true;
        applyThemeToAllScenes();
    }

    /**
     * 应用当前主题到所有已注册的 Scene
     */
    private static void applyThemeToAllScenes() {
        for (Scene scene : scenes) {
            applyThemeToScene(scene);
        }
    }

    /**
     * 应用当前主题到指定 Scene
     */
    private static void applyThemeToScene(Scene scene) {
        String stylesheet = isDarkMode ?
                ThemeUtils.class.getResource("/com/a360/fluentaqi/front/css/dark.css").toExternalForm() :
                ThemeUtils.class.getResource("/com/a360/fluentaqi/front/css/light.css").toExternalForm();

        scene.getStylesheets().clear();
        scene.getStylesheets().add(stylesheet);
    }
}
