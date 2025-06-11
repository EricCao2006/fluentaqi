package com.a360.fluentaqi.back.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * json文件读写
 * @author 夏从耀@A360
 */

public class JsonReader {
    // 线程安全的ObjectMapper实例
    private static final ObjectMapper objectMapper = createObjectMapper();

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 注册Java 8日期时间模块

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 空对象不报错
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }

    /**
     * 通用方法：将任意类型的List写入JSON文件
     * @param list 要写入的数据列表
     * @param filePath 目标文件路径
     * @param <T> 泛型类型
     */
    public static <T> void writeListToJson(List<T> list, String filePath) {
        try {
            Path path = Paths.get(filePath);
            // 确保目录存在
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            // 写入JSON
            objectMapper.writeValue(path.toFile(), list);
            System.out.println("数据写入成功: " + filePath);
        } catch (IOException e) {
            System.err.println("写入JSON文件失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 通用方法：从JSON文件读取任意类型的List
     * @param filePath JSON文件路径
     * @param typeReference 类型引用（用于泛型类型）
     * @return 读取到的数据列表，文件不存在或读取失败时返回空列表
     * @param <T> 泛型类型
     */
    public static <T> List<T> readListFromJson(String filePath, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在: " + filePath);
                return Collections.emptyList();
            }
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            System.err.println("读取JSON文件失败: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 简化方法：读取JSON文件到指定类型的List
     * @param filePath JSON文件路径
     * @param elementClass 列表元素类型
     * @return 读取到的数据列表
     * @param <T> 泛型类型
     */
    public static <T> List<T> readListFromJson(String filePath, Class<T> elementClass) {
        return readListFromJson(filePath, new TypeReference<List<T>>() {});
    }
}