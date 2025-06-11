package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.aqiabouts.Aqi;
import com.a360.fluentaqi.back.users.Supervisor;

import java.util.ArrayList;
import java.util.List;

public class AqiData {
    public static void main(String[] args) {
        List<Supervisor> slist = new ArrayList<Supervisor>();
        Aqi a1 = new Aqi("一级", "优", "空气质量令人满意,基本无空气污染");
        Aqi a2 = new Aqi("二级", "良", "空气质量可接受,但某些污染物可能对极少数异常敏感人群健康有较弱的影响");
        Aqi a3 = new Aqi("三级", "轻度污染", "易感人群症状有轻度加剧,健康人群出现刺激症状");
        Aqi a4 = new Aqi("四级", "中度污染", "进一步加剧易感人群症状,可能对健康人群心脏、呼吸系统有影响");
        Aqi a5 = new Aqi("五级", "重度污染", "心脏病和肺病患者症状显著加剧,运动耐受力降低,健康人群普遍出现症状");
        Aqi a6 = new Aqi("六级", "严重污染", "健康人群耐受力降低,有明显强烈症状,提前出现某些疾病");
        List<Aqi> alist = new ArrayList<Aqi>();
        alist.add(a1);
        alist.add(a2);
        alist.add(a3);
        alist.add(a4);
        alist.add(a5);
        alist.add(a6);
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        FileUtil.writeObject(ProPaht+"aqi.txt", alist);
    }
}
