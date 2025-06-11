package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.users.Admin;
import com.a360.fluentaqi.back.utils.JsonReader;

import java.util.ArrayList;
import java.util.List;

public class AdminData {
    public static void main(String[] args) {
        //管理员账号初始化
        Admin a1 = new Admin();
        a1.setLoginCode("1001");
        a1.setPassword("111");
        a1.setRealName("赵本山");
        Admin a2 = new Admin();
        a2.setLoginCode("1002");
        a2.setPassword("222");
        a2.setRealName("刘德华");
        List<Admin> alist = new ArrayList<Admin>();
        alist.add(a1);
        alist.add(a2);
        String ProPaht = System.getProperty("user.dir") + "src/main/resources/com/a360/fluentaqi/back/users";
        JsonReader.writeListToJson(alist,ProPaht+"admins.json");
    }
}
