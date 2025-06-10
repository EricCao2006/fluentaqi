package com.a360.fluentaqi.back.users;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户类<br>
 * 包含登录函数（返回布尔值）
 * @author 尚阳@A360
 */
public class User implements Serializable {
    //todo by 尚阳
    @Serial
    private static final long serialVersionUID = 1L;
    private String loginCode;//登录账号
    private String password ;//登录密码
    private String realName;//真实姓名
    public User(){
        super();
    }
    public User(String loginCode,String password,String realName){
        super();
        this.loginCode = loginCode;
        this.password = password;
        this.realName = realName;
    }
    public String getLoginCode(){
        return loginCode;
    }
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealName(){
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
}
