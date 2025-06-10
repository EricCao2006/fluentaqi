package com.a360.fluentaqi.back.utils;

import com.a360.fluentaqi.back.users.Admin;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * json文件读写
 * @author 夏从耀@A360
 */
public class JsonReader {
    // 获取当前项目类路径（读取json文件的时候会用到，对象格式数据用不到此对象）
    public static ClassLoader classLoader = JsonReader.class.getClassLoader();
    // 操作json格式数据是用的工具类对象（操作json文件的时候会用到，对象格式数据用不到此对象）
    public static ObjectMapper objectMapper = new ObjectMapper();


    // 用Jackson工具类读取json格式类型数据时，参照此方法内容
    public static List<Admin> getListByJackson() {

        List<Admin> admin = new ArrayList<>();
        try {
            // 通过类加载器加载json格式文件内容
            InputStream inputStream = classLoader.getResourceAsStream("com/a360/fluentaqi/back/users/Admin.json");
            // 将JSON数组字符串转换为Employee对象列表
            admin = objectMapper.readValue(inputStream, new TypeReference<List<Admin>>() {
            });

            // 打印解析后的对象列表
            for (Admin employee : admin) {
                System.out.println(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    // 用Jackson工具类写入json格式类型数据时，参照此方法内容
    public static void writeListByJson(List<Admin> admins) {
        try {
            // 获取输出流，指向类路径下的目标文件
            OutputStream outputStream =
                    new FileOutputStream(RWJsonTest.class.getClassLoader().getResource("NepDatas/JSONData/admins.json").getFile());


            // 下面的代码适用于创建新文件或覆盖现有文件的情况。
            // 如果你想追加数据，或者处理文件不存在的情况，需要做相应的逻辑调整。

            // 使用ObjectMapper将Employee对象列表序列化为JSON字符串，并写入输出流
            objectMapper.writeValue(outputStream, admins);

            // 关闭输出流
            outputStream.close();

            System.out.println("数据写入成功.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 调用读取json格式数据方法
        List<Admin> admins = getListByJackson();
        admins.add(new Admin(1, "1001", "111",null));
        // 调用写入json格式数据方法
        writeListByJson(admins);
    }
}
