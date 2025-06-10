package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.userService;
import com.a360.fluentaqi.back.users.Admin;
import com.a360.fluentaqi.back.users.User;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class userServiceImpl implements userService {
    @Override
    public boolean login(String loginCode, String password) {
        String ProPath = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 读取 JSON 文件并将其转换为 List<User>
            List<User> alist = objectMapper.readValue(new File(ProPath + "admin.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            for (User a : alist) {
                if (a.getLoginCode().equals(loginCode) && a.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}