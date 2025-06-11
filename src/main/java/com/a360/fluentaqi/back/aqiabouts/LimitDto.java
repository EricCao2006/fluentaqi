package com.a360.fluentaqi.back.aqiabouts;

/**
 * 污染级别的数据传输类<br>
 * Data transfer object
 * @author 尚阳@A360
 */
public class LimitDto {
    //todo by 尚阳
    private int intlevel;
    private String level;
    private String explain;
    private String color;

    public LimitDto(int intlevel, String level, String explain, String color) {
        super();
        this.intlevel = intlevel;
        this.level = level;
        this.explain = explain;
        this.color = color;
    }
    public LimitDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getIntlevel() {
        return intlevel;
    }
    public void setIntlevel(int intlevel) {
        this.intlevel = intlevel;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getExplain() {
        return explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
