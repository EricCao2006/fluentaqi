package com.a360.fluentaqi.back.services.impl;

import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.services.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    String FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/com/a360/fluentaqi/back/aqiabouts/aqi_feedback.json";
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void saveFeedBack(Feedback afb) {
        try {
            List<Feedback> afList = readJsonFile();
            afb.setAfId(afList.size()+1);
            afList.add(afb);
            writeJsonFile(afList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void assignGridder(String afId, String realName) {
        try {
            List<Feedback> alist = readJsonFile();
            for (Feedback af : alist) {
                if (af.getAfId().toString().equals(afId)) {
                    af.setGmName(realName);
                    af.setState("已指派");
                    break;
                }
            }
            writeJsonFile(alist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmData(Feedback afb) {
        try {
            List<Feedback> afList = readJsonFile();
            for (int i = 0; i < afList.size(); i++) {
                Feedback a = afList.get(i);
                if (a.getGmName() != null && a.getGmName().equals(afb.getGmName())
                        && a.getAfId().intValue() == afb.getAfId().intValue()) {
                    a.setState(afb.getState());
                    a.setConfirmDate(afb.getConfirmDate());
                    a.setCo(afb.getCo());
                    a.setSo2(afb.getSo2());
                    a.setPm(afb.getPm());
                    a.setConfirmLevel(afb.getConfirmLevel());
                    a.setConfirmExplain(afb.getConfirmExplain());
                    break;
                }
            }
            writeJsonFile(afList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //读Json文件并将其转化为对象列表
    private List<Feedback> readJsonFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Feedback.class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //将对象列表写入Json文件
    private void writeJsonFile(List<Feedback> afList) {
        try {
            // 确保目录存在
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            // 格式化输出，方便查看
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, afList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
