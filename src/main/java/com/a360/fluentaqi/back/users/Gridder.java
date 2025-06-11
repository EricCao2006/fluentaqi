package com.a360.fluentaqi.back.users;

import java.io.Serializable;

/**
 * 网格员子类
 * @author 尚阳@A360
 */
public class Gridder extends User implements Serializable {
    //todo by 尚阳
    private static final long serialVersionUID = 1L;
    private String gmTel;
    private String state;
    public String getGmTel() {
        return gmTel;
    }
    public void setGmTel(String gmTel) {
        this.gmTel = gmTel;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String toString() {
        return "GridMember [gmTel=" + gmTel + ", state=" + state + ", getLoginCode()=" + getLoginCode()
                + ", getPassword()=" + getPassword() + ", getRealName()=" + getRealName() + "]";
    }
}
