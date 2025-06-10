package com.a360.fluentaqi.back.services;

public interface userService {
/*
 * 用户登录
 * @param loginCode
 * @param password
 * @return
 */
    public boolean login(String loginCode,String password);
}
