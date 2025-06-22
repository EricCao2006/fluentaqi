package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.AdminService;
import com.a360.fluentaqi.back.users.Admin;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean login(String loginCode, String password) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/com/a360/fluentaqi/back/users/admins.json")) {
            if (is == null) {
                throw new RuntimeException("admins.json not found in classpath");
            }

            List<Admin> adminList = objectMapper.readValue(
                    is,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Admin.class)
            );
            for (Admin admin : adminList) {
                if (admin.getLoginCode() != null && admin.getLoginCode().equals(loginCode)
                        && admin.getPassword() != null && admin.getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //改了路径
}

