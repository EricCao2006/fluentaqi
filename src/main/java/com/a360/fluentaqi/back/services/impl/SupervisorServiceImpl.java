package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.SupervisorService;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.front.supervisor.SupervisorController;
import com.a360.fluentaqi.front.supervisor.historicaldata.HistoricalDataController;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SupervisorServiceImpl implements SupervisorService {
    @Override
    public boolean login(String loginCode, String password) {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/Supervisor.json";
        try {
            String jsonContent = Files.readString(Path.of(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Supervisor.class);
            List<Supervisor> slist = objectMapper.readValue(jsonContent, type);

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
        String filePath = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/Supervisor.json";
        List<Supervisor> slist = new ArrayList<>();

        try {
            // 检查文件是否存在
            Path path = Path.of(filePath);
            if (Files.exists(path)) {
                String jsonContent = Files.readString(path);
                ObjectMapper objectMapper = new ObjectMapper();
                JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Supervisor.class);
                slist = objectMapper.readValue(jsonContent, type);

                for (Supervisor s : slist) {
                    if (s.getLoginCode().equals(supervisor.getLoginCode())) {
                        return false;
                    }
                }
            }

            slist.add(supervisor);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), slist);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            // 处理文件操作异常
            return false;
        }
    }
}
