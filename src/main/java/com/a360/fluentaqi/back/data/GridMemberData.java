package com.a360.fluentaqi.back.data;

import com.a360.fluentaqi.back.users.Gridder;

import java.util.ArrayList;
import java.util.List;

public class GridMemberData {
    public static void main(String[] args) {
        //管网格员账号初始化
        Gridder gm1 = new Gridder();
        gm1.setLoginCode("2024001");
        gm1.setPassword("111");
        gm1.setRealName("郭晓川");
        gm1.setGmTel("13888888888");
        gm1.setState("工作中");
        GridMember gm2 = new GridMember();
        gm2.setLoginCode("2024002");
        gm2.setPassword("222");
        gm2.setRealName("韩德君");
        gm2.setGmTel("13555555555");
        gm2.setState("工作中");
        GridMember gm3 = new GridMember();
        gm3.setLoginCode("2024003");
        gm3.setPassword("333");
        gm3.setRealName("李晓旭");
        gm3.setGmTel("13444444444");
        gm3.setState("工作中");
        GridMember gm4 = new GridMember();
        gm4.setLoginCode("2024004");
        gm4.setPassword("444");
        gm4.setRealName("赵继伟");
        gm4.setGmTel("13222222222");
        gm4.setState("休假中");
        GridMember gm5 = new GridMember();
        gm5.setLoginCode("2024005");
        gm5.setPassword("555");
        gm5.setRealName("易建联");
        gm5.setGmTel("13666666666");
        gm5.setState("工作中");
        List<GridMember> glist = new ArrayList<GridMember>();
        glist.add(gm1);
        glist.add(gm2);
        glist.add(gm3);
        glist.add(gm4);
        glist.add(gm5);
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/ObjectData/";
        FileUtil.writeObject(ProPaht+"gridmember.txt", glist);
    }
}
