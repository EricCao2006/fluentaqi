package com.a360.fluentaqi.back.utils;

/**
 * 时间与日期格式化
 * @author 夏从耀@A360
 */
//public class TimeFormater {
//
//    //todo by 夏从耀
//}


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

public class TimeFormater extends Application {

    private final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private ComboBox<String> formatComboBox;
    private ComboBox<String> timezoneComboBox;
    private TextField customFormatField;
    private TextField inputField;
    private TextArea outputArea;
    private Label currentTimeLabel;

    @Override
    public void start(Stage primaryStage) {
        // 创建主布局
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f5f7fa;");

        // 创建标题
        Label titleLabel = new Label("东软环保平台 - 时间日期格式化工具");
        titleLabel.setFont(Font.font("Microsoft YaHei", 24));
        titleLabel.setTextFill(Color.valueOf("#2c3e50"));

        // 创建输入区域
        GridPane inputGrid = createInputGrid();

        // 创建控制区域
        HBox controlBox = createControlBox();

        // 创建输出区域
        VBox outputBox = createOutputBox();

        // 创建当前时间区域
        HBox currentTimeBox = createCurrentTimeBox();

        // 添加所有组件到主布局
        mainLayout.getChildren().addAll(
                titleLabel,
                inputGrid,
                controlBox,
                outputBox,
                currentTimeBox
        );

        // 创建场景
        Scene scene = new Scene(mainLayout, 800, 600);

        // 设置舞台
        primaryStage.setTitle("东软环保平台 - 时间日期格式化工具");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 启动当前时间更新线程
        startTimeUpdateThread();
    }

    private GridPane createInputGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));
        grid.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 5; -fx-border-color: #d1d8e0; -fx-border-width: 1;");

        // 输入标签
        Label inputLabel = new Label("输入日期/时间:");
        inputLabel.setFont(Font.font("Microsoft YaHei", 14));

        // 输入字段
        inputField = new TextField();
        inputField.setPromptText("例如: 2023-08-15 14:30:00 或 当前时间");
        inputField.setPrefHeight(35);

        // 格式选择标签
        Label formatLabel = new Label("输出格式:");
        formatLabel.setFont(Font.font("Microsoft YaHei", 14));

        // 格式选择框
        formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll(
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "yyyy年MM月dd日 HH时mm分ss秒",
                "MM/dd/yyyy hh:mm:ss a",
                "ISO 8601",
                "RFC 1123",
                "Unix时间戳",
                "自定义格式"
        );
        formatComboBox.getSelectionModel().select(0);
        formatComboBox.setPrefWidth(250);

        // 时区选择标签
        Label timezoneLabel = new Label("时区:");
        timezoneLabel.setFont(Font.font("Microsoft YaHei", 14));

        // 时区选择框
        timezoneComboBox = new ComboBox<>();
        timezoneComboBox.getItems().addAll(TimeZone.getAvailableIDs());
        timezoneComboBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String id) {
                TimeZone tz = TimeZone.getTimeZone(id);
                return String.format("(UTC%s) %s",
                        tz.getDisplayName(false, TimeZone.SHORT),
                        tz.getID());
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        });
        timezoneComboBox.getSelectionModel().select(TimeZone.getDefault().getID());
        timezoneComboBox.setPrefWidth(250);

        // 自定义格式标签
        Label customFormatLabel = new Label("自定义格式:");
        customFormatLabel.setFont(Font.font("Microsoft YaHei", 14));
        customFormatLabel.setVisible(false);

        // 自定义格式字段
        customFormatField = new TextField();
        customFormatField.setPromptText("输入自定义格式，例如: yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        customFormatField.setPrefHeight(35);
        customFormatField.setVisible(false);

        // 添加组件到网格
        grid.add(inputLabel, 0, 0);
        grid.add(inputField, 1, 0, 2, 1);
        grid.add(formatLabel, 0, 1);
        grid.add(formatComboBox, 1, 1);
        grid.add(timezoneLabel, 0, 2);
        grid.add(timezoneComboBox, 1, 2);
        grid.add(customFormatLabel, 0, 3);
        grid.add(customFormatField, 1, 3);

        // 格式选择变化监听器
        formatComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean showCustom = "自定义格式".equals(newVal);
            customFormatLabel.setVisible(showCustom);
            customFormatField.setVisible(showCustom);
        });

        // 设置列约束
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(250);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(col1, col2, col3);

        return grid;
    }

    private HBox createControlBox() {
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER);

        // 格式化按钮
        Button formatButton = new Button("格式化");
        formatButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        formatButton.setPrefSize(120, 40);
        formatButton.setOnAction(e -> formatDateTime());

        // 当前时间按钮
        Button currentTimeButton = new Button("使用当前时间");
        currentTimeButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        currentTimeButton.setPrefSize(150, 40);
        currentTimeButton.setOnAction(e -> {
            inputField.setText("当前时间");
            formatDateTime();
        });

        // 复制结果按钮
        Button copyButton = new Button("复制结果");
        copyButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-weight: bold;");
        copyButton.setPrefSize(120, 40);
        copyButton.setOnAction(e -> {
            if (outputArea.getText() != null && !outputArea.getText().isEmpty()) {
                Clipboard.getSystemClipboard().setContent(
                        Collections.singletonMap(DataFormat.PLAIN_TEXT, outputArea.getText())
                );
                showAlert("复制成功", "结果已复制到剪贴板");
            }
        });

        // 清空按钮
        Button clearButton = new Button("清空");
        clearButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        clearButton.setPrefSize(120, 40);
        clearButton.setOnAction(e -> {
            inputField.clear();
            outputArea.clear();
        });

        box.getChildren().addAll(formatButton, currentTimeButton, copyButton, clearButton);
        return box;
    }

    private VBox createOutputBox() {
        VBox box = new VBox(10);

        Label outputLabel = new Label("格式化结果:");
        outputLabel.setFont(Font.font("Microsoft YaHei", 16));

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(200);
        outputArea.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14;");

        box.getChildren().addAll(outputLabel, outputArea);
        return box;
    }

    private HBox createCurrentTimeBox() {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #34495e; -fx-padding: 10; -fx-background-radius: 5;");

        Label label = new Label("当前系统时间:");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Microsoft YaHei", 14));

        currentTimeLabel = new Label();
        currentTimeLabel.setTextFill(Color.WHITE);
        currentTimeLabel.setFont(Font.font("Microsoft YaHei", 16));

        box.getChildren().addAll(label, currentTimeLabel);
        return box;
    }

    private void startTimeUpdateThread() {
        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                try {
                    // 更新UI需要在JavaFX应用线程执行
                    javafx.application.Platform.runLater(() -> {
                        LocalDateTime now = LocalDateTime.now();
                        String formattedTime = now.format(defaultFormatter);
                        currentTimeLabel.setText(formattedTime);
                    });

                    // 每秒更新一次
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    private void formatDateTime() {
        String input = inputField.getText().trim();
        String formatType = formatComboBox.getValue();
        String timezoneId = timezoneComboBox.getValue();
        String customFormat = customFormatField.getText().trim();

        if (input.isEmpty() || "当前时间".equalsIgnoreCase(input)) {
            input = LocalDateTime.now().format(defaultFormatter);
            inputField.setText(input);
        }

        try {
            // 解析输入时间
            LocalDateTime dateTime = parseDateTime(input);

            // 转换为指定时区
            ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault())
                    .withZoneSameInstant(ZoneId.of(timezoneId));

            // 格式化输出
            String formattedOutput;
            switch (formatType) {
                case "ISO 8601":
                    formattedOutput = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    break;
                case "RFC 1123":
                    DateTimeFormatter rfcFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;
                    formattedOutput = zonedDateTime.format(rfcFormatter);
                    break;
                case "Unix时间戳":
                    long epochSecond = zonedDateTime.toEpochSecond();
                    formattedOutput = String.valueOf(epochSecond);
                    break;
                case "自定义格式":
                    if (customFormat.isEmpty()) {
                        formattedOutput = "请输入自定义格式";
                    } else {
                        try {
                            formattedOutput = zonedDateTime.format(DateTimeFormatter.ofPattern(customFormat));
                        } catch (IllegalArgumentException e) {
                            formattedOutput = "无效的自定义格式: " + e.getMessage();
                        }
                    }
                    break;
                default:
                    formattedOutput = zonedDateTime.format(DateTimeFormatter.ofPattern(formatType));
            }

            // 创建结果输出
            StringBuilder result = new StringBuilder();
            result.append("原始输入: ").append(input).append("\n");
            result.append("解析时间: ").append(dateTime.format(defaultFormatter)).append("\n");
            result.append("目标时区: ").append(timezoneId).append("\n");
            result.append("格式化结果: ").append(formattedOutput).append("\n\n");
            result.append("时区信息:\n");
            result.append("  - 时区ID: ").append(timezoneId).append("\n");
            result.append("  - 时区名称: ").append(ZoneId.of(timezoneId).getDisplayName(TextStyle.FULL, Locale.CHINA)).append("\n");
            result.append("  - 当前偏移: ").append(zonedDateTime.getOffset());

            outputArea.setText(result.toString());
        } catch (Exception e) {
            outputArea.setText("错误: " + e.getMessage() +
                    "\n\n支持的格式示例:\n" +
                    "  - yyyy-MM-dd HH:mm:ss\n" +
                    "  - yyyy/MM/dd HH:mm:ss\n" +
                    "  - yyyy年MM月dd日 HH时mm分ss秒\n" +
                    "  - MM/dd/yyyy hh:mm:ss a\n" +
                    "  - 当前时间");
        }
    }

    private LocalDateTime parseDateTime(String input) throws ParseException {
        // 尝试多种常见格式
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "yyyy年MM月dd日 HH时mm分ss秒",
                "MM/dd/yyyy hh:mm:ss a",
                "yyyy-MM-dd",
                "HH:mm:ss"
        );

        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.setLenient(false);
                Date date = sdf.parse(input);
                return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            } catch (ParseException ignored) {
                // 尝试下一个格式
            }
        }

        // 如果都不匹配，尝试解析为Unix时间戳
        try {
            long timestamp = Long.parseLong(input);
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            throw new ParseException("无法解析日期时间: " + input, 0);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
