package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.services.GridderService;
import com.a360.fluentaqi.back.users.Gridder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class GridderServiceImpl implements GridderService {
    @Override
    public Gridder login(String loginCode, String password) {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/users/grid_member";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Gridder> gridderList;
        try {
            gridderList = objectMapper.readValue(
                    new File(filePath),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Gridder.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (Gridder gridder : gridderList) {
            if(gridder.getLoginCode().equals(loginCode) && gridder.getPassword().equals(password)){
                return gridder;
            }
        }
        return null;
    }
}
