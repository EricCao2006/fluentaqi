package com.a360.fluentaqi.back.aqiabouts;

import java.util.ArrayList;
import java.util.List;

/**
 * 省-市存储类
 * @author 尚阳@A360
 */
public class Province {
    //todo by 尚阳
    private static final long serialVersionUID = 1L;
    private Integer provinceId;
    private String provinceName;
    private List<String> cityName = new ArrayList<String>();

    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public List<String> getCityName() {
        return cityName;
    }
    public void setCityName(List<String> cityName) {
        this.cityName = cityName;
    }
}
