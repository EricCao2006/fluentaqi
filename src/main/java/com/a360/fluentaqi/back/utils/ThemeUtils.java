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
     * 默认浅色模式
     */
    public static void register(Scene scene) {
        if (scenes.contains(scene)) {
            return; // 避免重复注册
        }
        scenes.add(scene);
        applyThemeToScene(scene);
    }


    /*模式切换*/
    public static void changeTheme() {
        isDarkMode = !isDarkMode;
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

    /**
     * 获取当前主题模式
     */
    public static boolean getIsDarkMode() {
        return isDarkMode;
    }
}
