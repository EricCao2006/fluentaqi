package com.a360.fluentaqi.back.services;

import java.io.IOException;

public interface AdminService {
    /**
     * 管理员登录
     * @param loginCode
     * @param password
     * @return
     */
    public boolean login(String loginCode,String password) throws IOException;
}
