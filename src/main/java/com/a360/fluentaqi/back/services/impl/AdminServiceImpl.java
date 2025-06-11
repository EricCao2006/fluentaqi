package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.AdminService;
import com.a360.fluentaqi.back.users.Admin;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.back.users.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean login(String loginCode, String password) {
        // TODO Auto-generated method stub
        String filePath = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Admin> adminList = objectMapper.readValue(
                    new File(filePath),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Admin.class)
            );
            for (Admin admin : adminList) {
                if (admin.getLoginCode().equals(loginCode) && admin.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
