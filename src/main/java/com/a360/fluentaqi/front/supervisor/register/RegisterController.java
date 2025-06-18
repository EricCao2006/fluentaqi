package com.a360.fluentaqi.front.supervisor.register;

import com.a360.fluentaqi.back.services.SupervisorService;

import com.a360.fluentaqi.LoginController;
import com.a360.fluentaqi.back.users.Supervisor;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField loginCode;

    @FXML
    private PasswordField password;

    @FXML
    private TextField txt_realName;

    @FXML
    private ChoiceBox<?> txt_sex;
    public static Stage primaryStage;
    public ChoiceBox<?> getTxt_sex(){
        return txt_sex;
    }
    public TextField getTxt_realName(){
        return txt_realName;
    }
    public PasswordField getPassword(){
        return password;
    }
    public TextField getLoginCode(){
        return loginCode;
//非常无语，这个账号就是手机号
    }
    public PasswordField getConfirmPassword(){
        return confirmPassword;
    }
    public void setTxt_realName(TextField txt_realName){
        this.txt_realName = txt_realName;
    }
    public void setLoginCode(TextField loginCode){
        this.loginCode = loginCode;
    }
    public void setPassword(PasswordField password){
        this.password = password;
    }
    public void setConfirmPassword(PasswordField confirmPassword){
        this.confirmPassword = confirmPassword;
    }
    @FXML
    void getback() {
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml", LoginController.primaryStage,"Fluent AQI 登录");
    }
    public void register(){
        if(!password.getText().equals(confirmPassword.getText())){
            JavafxUtil.showAlert(primaryStage, "注册失败", "两次输入密码不一致", "请重新输入确认密码","warn");
            confirmPassword.setText("");
            return;
        }
        Supervisor supervisor = new Supervisor();
        supervisor.setLoginCode(loginCode.getText());
        supervisor.setPassword(password.getText());
        supervisor.setRealName(txt_realName.getText());
        supervisor.setSex((String) txt_sex.getValue());
        SupervisorService supervisorService = new SupervisorService() {
            @Override
            public boolean login(String loginCode, String password) {
                return false;
            }

            @Override
            public boolean register(Supervisor supervisor) {
                // 实现注册逻辑
                return true; // 示例返回值
            }
        };
        boolean flag = supervisorService.register(supervisor);
        if(flag){
            JavafxUtil.showAlert(primaryStage, "注册成功", loginCode.getText()+" 账号注册成功!","可以进行用户登录!" ,"info");
            JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml", LoginController.primaryStage,"Fluent AQI 登录");
        }else{
            JavafxUtil.showAlert(primaryStage, "注册失败", "手机号已被注册", "请重新输入注册手机号码","warn");
            loginCode.setText("");
            return;
        }
    }
}
