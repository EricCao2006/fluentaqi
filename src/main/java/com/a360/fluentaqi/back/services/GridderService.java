package com.a360.fluentaqi.back.services;

import com.a360.fluentaqi.back.users.Gridder;

public interface GridderService {
    /**
     * 网格员登录
     * @param loginCode
     * @param password
     * @return
     */
    public Gridder login(String loginCode, String password);
}
