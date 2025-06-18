package com.a360.fluentaqi.back.aqiabouts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * AQI反馈信息
 * @author 尚阳@A360
 */
public class Feedback {
    private static final long serialVersionUID = 1L;
    private Integer afId;		//������Ϣ���
    private String afName;	//���ڼලԱ����
    private String provinceName;	//ʡ��������
    private String cityName;	//����������
    private String address;		//�����ַ
    private String information;	//��ϸ������Ϣ
    private String estimate_grade;//Ԥ���ȼ�
    private String af_date;		//��������
    private String state;		//����״̬: δָ��,��ָ��,��ʵ��
    private String gmName;		//ָ������Ա
    private String confirmDate;	//ʵ������
    private Double so2;			//ʵ���������Ũ��
    private Double co;			//ʵ��һ����̼Ũ��
    private Double pm;			//ʵ��PM2.5Ũ��
    private String confirmLevel;//ʵ��AQI�ȼ�
    private String confirmExplain;//ʵ��AQI�ȼ�����

    public Integer getAfId() {
        return afId;
    }
    public void setAfId(Integer afId) {
        this.afId = afId;
    }
    public String getAfName() {
        return afName;
    }
    public void setAfName(String afName) {
        this.afName = afName;
    }
    public String getProvinceName() {
        return provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String getEstimateGrade() {
        return estimate_grade;
    }
    public void setEstimateGrade(String estimateGrade) {
        this.estimate_grade = estimateGrade;
    }
    public String getDate() {
        return af_date;
    }
    public void setDate(String af_date) {
        this.af_date = af_date;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getGmName() {
        return gmName;
    }
    public void setGmName(String gmName) {
        this.gmName = gmName;
    }
    public Double getSo2() {
        return so2;
    }
    public void setSo2(Double so2) {
        this.so2 = so2;
    }
    public Double getCo() {
        return co;
    }
    public void setCo(Double co) {
        this.co = co;
    }
    public Double getPm() {
        return pm;
    }
    public void setPm(Double pm) {
        this.pm = pm;
    }

    public String getConfirmDate() {
        return confirmDate;
    }
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }
    public String getConfirmLevel() {
        return confirmLevel;
    }
    public void setConfirmLevel(String confirmLevel) {
        this.confirmLevel = confirmLevel;
    }
    public String getConfirmExplain() {
        return confirmExplain;
    }
    public void setConfirmExplain(String confirmExplain) {
        this.confirmExplain = confirmExplain;
    }
    @Override
    public String toString() {
        return "AqiFeedback [afId=" + afId + ", afName=" + afName + ", provinceName=" + provinceName + ", cityName="
                + cityName + ", address=" + address + ", information=" + information + ", estimate_grade=" + estimate_grade
                + ", af_date=" + af_date + ", state=" + state + ", gmName=" + gmName + ", confirmDate=" + confirmDate
                + ", so2=" + so2 + ", co=" + co + ", pm=" + pm + ", confirmLevel=" + confirmLevel + ", confirmExplain="
                + confirmExplain + "]";
    }


}
