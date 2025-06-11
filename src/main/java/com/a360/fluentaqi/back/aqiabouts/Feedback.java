package com.a360.fluentaqi.back.aqiabouts;

/**
 * AQI反馈信息
 * @author 尚阳@A360
 */
public class Feedback {
    //todo by 尚阳
    private static final long serialVersionUID = 1L;
    private Integer afId;		//������Ϣ���
    private String afName;	//���ڼලԱ����
    private String proviceName;	//ʡ��������
    private String cityName;	//����������
    private String address;		//�����ַ
    private String infomation;	//��ϸ������Ϣ
    private String estimateGrade;//Ԥ���ȼ�
    private String date;		//��������
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
    public String getProviceName() {
        return proviceName;
    }
    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
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
    public String getInfomation() {
        return infomation;
    }
    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }
    public String getEstimateGrade() {
        return estimateGrade;
    }
    public void setEstimateGrade(String estimateGrade) {
        this.estimateGrade = estimateGrade;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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
        return "AqiFeedback [afId=" + afId + ", afName=" + afName + ", proviceName=" + proviceName + ", cityName="
                + cityName + ", address=" + address + ", infomation=" + infomation + ", estimateGrade=" + estimateGrade
                + ", date=" + date + ", state=" + state + ", gmName=" + gmName + ", confirmDate=" + confirmDate
                + ", so2=" + so2 + ", co=" + co + ", pm=" + pm + ", confirmLevel=" + confirmLevel + ", confirmExplain="
                + confirmExplain + "]";
    }
}
