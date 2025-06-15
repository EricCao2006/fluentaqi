package com.a360.fluentaqi;

import com.a360.fluentaqi.back.services.AdminService;
import com.a360.fluentaqi.back.services.impl.AdminServiceImpl;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.front.admin.AdminController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{

    @FXML
    private TextField txt_id;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ChoiceBox<?> txt_type;

    private AdminService adminService = new AdminServiceImpl();

    public static Stage primaryStage;

    public  TextField getTxt_id(){
        return txt_id;
    }

    public PasswordField getTxt_password()
    {
        return txt_password;
    }

    public ChoiceBox<?> getTxt_type(){
        return txt_type;
    }

    public void setTxt_id(TextField txt_id) {
        this.txt_id = txt_id;
    }

    public void setTxt_password(PasswordField txt_password) {
        this.txt_password = txt_password;
    }

    @FXML
    public void login() throws IOException {
        switch(txt_type.getValue().toString()){
            case "管理员":
                boolean isLogin = adminService.login(txt_id.getText(), txt_password.getText());
                if(isLogin){
                    AdminController.primaryStage = primaryStage;
                    JavafxUtil.showStage(AdminController.class,"com/a360/fluentaqi/front/admin/view.fxml", primaryStage,"环保公众监督平台-管理端-主功能界面");
                }else{
                    JavafxUtil.showAlert(primaryStage, "登录失败", "用户名密码错误", "请重新输入用户名和密码","warn");
                }
        }

    }

}

