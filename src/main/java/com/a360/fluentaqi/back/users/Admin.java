package com.a360.fluentaqi.back.users;

import java.io.Serializable;

/**
 * 管理员子类
 * @author 尚阳@A360
 */
public class Admin extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Admin_id;
    public String getAdmin_id(){
        return Admin_id;
    }
    public String setAdmin_id(){
        this.Admin_id = Admin_id;
    }
    @Override
    public String toString() {
        return "Admin [getLoginCode()=" + getLoginCode() + ", getPassword()=" + getPassword() + ", getRealName()="
                + getRealName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }
}
