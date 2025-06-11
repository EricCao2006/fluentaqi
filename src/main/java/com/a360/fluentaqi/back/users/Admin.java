package com.a360.fluentaqi.back.users;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 管理员子类
 * @author 尚阳@A360
 */
public class Admin extends User implements Serializable {
    //todo by 尚阳
    private static final long serialVersionUID = 568262933260141034L;

    @JsonProperty("admin_id")
    private int adminId;

    @JsonProperty("admin_code")
    private String adminCode;

    private String password;
    private String remarks;

    public Admin() {
        super();
    }
    public Admin(int adminId, String adminCode, String password, String remarks) {
        super();
        this.adminId = adminId;
        this.adminCode = adminCode;
        this.password = password;
        this.remarks = remarks;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public int getAdminId() {
        return this.adminId;
    }
    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }
    public String getAdminCode() {
        return this.adminCode;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getRemarks() {
        return this.remarks;
    }
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adminCode=" + adminCode + ", password=" + password + ", remarks="
                + remarks + "]";
    }
}
