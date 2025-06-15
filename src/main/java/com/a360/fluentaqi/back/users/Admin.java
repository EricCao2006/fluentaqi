package com.a360.fluentaqi.back.users;

import java.io.Serializable;

/**
 * 管理员子类
 * @author 尚阳@A360
 */
public class Admin extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Admin_id;
    private String Admin_code;
    private String remarks;
    public String getAdmin_id(){
        return Admin_id;
    }
    public void setAdmin_id(String Admin_id){
        this.Admin_id = Admin_id;
    }
    public String getAdmin_code(){
        return Admin_code;
    }
    public void setAdmin_code(String admin_code){
        this.Admin_code = admin_code;
    }
    public String getRemarks(){
        return remarks;
    }
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }
    @Override
    public String toString() {
        return "Admin [getLoginCode()=" + getLoginCode() + ", getPassword()=" + getPassword() + ", getRealName()="
                + getRealName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }
}
