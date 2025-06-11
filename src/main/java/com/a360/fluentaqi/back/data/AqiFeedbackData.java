package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.aqiabouts.Feedback;

import java.util.ArrayList;
import java.util.List;

public class AqiFeedbackData {
    public static void main(String[] args) {
        List<Feedback> afList = new ArrayList<Feedback>();

        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        FileUtil.writeObject(ProPaht+"aqifeedback.txt", afList);

    }
}
