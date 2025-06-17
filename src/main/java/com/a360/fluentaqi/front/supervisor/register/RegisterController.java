package com.a360.fluentaqi.front.supervisor.register;

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
        JavafxUtil.showStage(this.getClass(),"/com/a360/fluentaqi/front/view.fxml",primaryStage,"环保公共监督平台");
    }

}
