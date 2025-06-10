package com.a360.fluentaqi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController{

    @FXML
    private TextField txt_id;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ChoiceBox<?> txt_type;

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
    public void login(ActionEvent event) {
        boolean islogin = ;
        switch (txt_type.getValue().toString()){
            case 管理员：



        }
    }

}

