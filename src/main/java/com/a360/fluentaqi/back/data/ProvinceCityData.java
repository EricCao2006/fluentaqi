package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.aqiabouts.Province;

import java.util.ArrayList;
import java.util.List;

public class ProvinceCityData {
    public static void main(String[] args) {
        Province p1 = new Province();
        p1.setProvinceId(1001);
        p1.setProvinceName("辽宁省");
        List<String> city1 = new ArrayList<String>();
        city1.add("沈阳市");
        city1.add("大连市");
        city1.add("鞍山市");
        city1.add("抚顺市");
        p1.setCityName(city1);

        Province p2 = new Province();
        p2.setProvinceId(1002);
        p2.setProvinceName("吉林省");
        List<String> city2 = new ArrayList<String>();
        city2.add("长春市");
        city2.add("吉林市");
        city2.add("四平市");
        p2.setCityName(city2);

        Province p3 = new Province();
        p3.setProvinceId(1003);
        p3.setProvinceName("黑龙江省");
        List<String> city3 = new ArrayList<String>();
        city3.add("哈尔滨市");
        city3.add("大庆市");
        city3.add("齐齐哈尔市");
        p3.setCityName(city3);

        List<Province> plist = new ArrayList<Province>();
        plist.add(p1);
        plist.add(p2);
        plist.add(p3);
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        FileUtil.writeObject(ProPaht+"province_city.txt", plist);
    }
}
