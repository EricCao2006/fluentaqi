package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.SupervisorService;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.front.supervisor.SupervisorController;
import com.a360.fluentaqi.front.supervisor.historicaldata.HistoricalDataController;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SupervisorServiceImpl implements SupervisorService {
    @Override
    public boolean login(String loginCode, String password) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/com/a360/fluentaqi/back/users/supervisor.json")) {
            if (is == null) {
                throw new RuntimeException("supervisor.json not found in classpath");
            }

            List<Supervisor> slist = objectMapper.readValue(
                    is,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Supervisor.class)
            );
            for (Supervisor s : slist) {
                if (s.getLoginCode().equals(loginCode) && s.getPassword().equals(password)) {
                    SupervisorController.supervisor = s;
                    HistoricalDataController.supervisor = s;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 处理文件读取异常
        }
        return false;
    }

    @Override
    public boolean register(Supervisor supervisor) {
        ObjectMapper objectMapper = new ObjectMapper();
        String resourcePath = "/com/a360/fluentaqi/back/users/Supervisor.json";

        List<Supervisor> slist = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is != null) {
                // 读取现有数据
                String jsonContent = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Supervisor.class);
                slist = objectMapper.readValue(jsonContent, type);

                // 检查是否已存在相同 loginCode
                for (Supervisor s : slist) {
                    if (s.getLoginCode().equals(supervisor.getLoginCode())) {
                        return false;
                    }
                }
            } else {
                System.out.println("Resource not found: " + resourcePath);
            }

            // 添加新用户
            slist.add(supervisor);

            // 写回文件（需要真实路径）
            String userDir = System.getProperty("user.dir");
            String filePath = userDir + "/src/main/resources/com/a360/fluentaqi/back/users/Supervisor.json";
            File file = new File(filePath);

            // 确保目录存在
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            // 写入更新后的列表
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, slist);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to read or write Supervisor.json: " + e.getMessage());
            return false;
        }
    }

}
