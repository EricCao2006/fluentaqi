package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.aqiabouts.Feedback;
import com.a360.fluentaqi.back.utils.JsonReader;

import java.util.ArrayList;
import java.util.List;

public class AqiFeedbackData {
    public static void main(String[] args) {
        List<Feedback> afList = new ArrayList<Feedback>();

        String ProPaht = System.getProperty("user.dir") + "src/main/resources/com/a360/fluentaqi/back/users";
        JsonReader.writeListToJson(afList,ProPaht+"aqi_feedback.json");

    }
}
