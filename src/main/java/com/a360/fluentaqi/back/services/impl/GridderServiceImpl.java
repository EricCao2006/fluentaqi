package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.GridderService;
import com.a360.fluentaqi.back.users.Gridder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class GridderServiceImpl implements GridderService {
    @Override
    public Gridder login(String loginCode, String password) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/com/a360/fluentaqi/back/users/grid_member.json")) {
            if (is == null) {
                throw new RuntimeException("grid_member.json not found in classpath");
            }

            List<Gridder> gridderList = objectMapper.readValue(
                    is,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Gridder.class)
            );

            for (Gridder gridder : gridderList) {
                if(gridder.getLoginCode().equals(loginCode) && gridder.getPassword().equals(password)){
                    return gridder;
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
