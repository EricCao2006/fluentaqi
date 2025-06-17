package com.a360.fluentaqi.back.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 监督员子类
 * @author 尚阳@A360
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Supervisor extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String sex ;
    private String birthday;
    public Supervisor() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Supervisor(String loginCode,String password,String realName, String sex) {
        super(loginCode,password,realName);
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirthday(){
        return birthday;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return "Supervisor [sex=" + sex + ", getLoginCode()=" + getLoginCode() + ", getPassword()=" + getPassword()
                + ", getRealName()=" + getRealName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}
