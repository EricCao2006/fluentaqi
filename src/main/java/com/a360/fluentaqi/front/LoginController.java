package com.a360.fluentaqi.front;

import com.a360.fluentaqi.back.services.AdminService;
import com.a360.fluentaqi.back.services.GridderService;
import com.a360.fluentaqi.back.services.SupervisorService;
import com.a360.fluentaqi.back.services.impl.AdminServiceImpl;
import com.a360.fluentaqi.back.services.impl.GridderServiceImpl;
import com.a360.fluentaqi.back.services.impl.SupervisorServiceImpl;
import com.a360.fluentaqi.back.users.Gridder;
import com.a360.fluentaqi.back.utils.JavafxUtil;
import com.a360.fluentaqi.front.admin.AdminController;
import com.a360.fluentaqi.front.gridder.GridderController;
import com.a360.fluentaqi.front.supervisor.SupervisorController;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginController{

    @FXML
    private TextField txt_id;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ChoiceBox<?> txt_type;

    private AdminService adminService = new AdminServiceImpl();

    private GridderService gridMemberService = new GridderServiceImpl();

    public SupervisorService supervisorService = new SupervisorServiceImpl();

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
        if (txt_type.getValue() == null) {
            JavafxUtil.showAlert(primaryStage, "数据错误", "登录类型未选择", "请选择登录类型", "warn");
            return;
        }
        switch(txt_type.getValue().toString()){
            case "管理员":
                boolean isLogin = adminService.login(txt_id.getText(), txt_password.getText());
                if(isLogin){
                    AdminController.primaryStage = primaryStage;
                    JavafxUtil.showStage(LoginRunner.class,"/com/a360/fluentaqi/front/admin/view.fxml", primaryStage,"环保公众监督平台-管理端-主功能界面");
                }else{
                    JavafxUtil.showAlert(primaryStage, "登录失败", "用户名密码错误", "请重新输入用户名和密码","warn");
                }
                break;
            case "网格员":
                if(txt_id.getText().equals("")){
                    JavafxUtil.showAlert(primaryStage, "数据格式错误", "登录账号不能为空", "请重新输入登录账号","warn");
                    return;
                }
                if(txt_password.getText().equals("")){
                    JavafxUtil.showAlert(primaryStage, "数据格式错误", "登录密码不能为空", "请重新输入登录密码","warn");
                    return;
                }
                GridderController.primaryStage = primaryStage;
                Gridder gm = gridMemberService.login(txt_id.getText(), txt_password.getText());
                if(gm!=null){
                    GridderController.gridMember = gm;
                    JavafxUtil.showStage(LoginRunner.class, "/com/a360/fluentaqi/front/gridder/view.fxml", primaryStage, "环保公众监督平台-确认AQI反馈数据");

                }else{
                    JavafxUtil.showAlert(primaryStage, "登录失败", "登录账号和密码错误","请重新输入账号和密码","warn");
                }
                break;
            case "监督员":
                boolean flag = supervisorService.login(txt_id.getText(), txt_password.getText());
                if(flag){
                    SupervisorController.primaryStage = primaryStage;
                    JavafxUtil.showStage(LoginRunner.class,"/com/a360/fluentaqi/front/supervisor/view.fxml", primaryStage, "环保公众监督平台-公众监督员端-AQI数据反馈");
                }else{
                    JavafxUtil.showAlert(primaryStage, "登录失败", "用户名密码错误", "","warn");
                }
                break;
            default:
        }

    }
    @FXML
    void toRegister() {
//        System.out.println("注册按钮被点击");
        try {
            JavafxUtil.showStage(LoginRunner.class,
                    "/com/a360/fluentaqi/front/supervisor/register/view.fxml",
                    primaryStage,
                    "环保公众监督平台-监督员端-注册界面");
        } catch (Exception e) {
            e.printStackTrace(); // 捕获可能的异常
        }
    }

    @FXML
    private void showSettingsMenu(ActionEvent event) {
        if (floatingMenu == null) {
            // 创建圆形按钮1
            Button btnEdit = new Button("✏️");
            btnEdit.getStyleClass().add("round-button");

            // 创建圆形按钮2
            Button btnHelp = new Button("❓");
            btnHelp.getStyleClass().add("round-button");

            // 设置点击事件
            btnEdit.setOnAction(e -> System.out.println("编辑按钮被点击"));
            btnHelp.setOnAction(e -> System.out.println("帮助按钮被点击"));

            // 创建浮动菜单容器
            floatingMenu = new VBox(10); // 按钮间距
            floatingMenu.getChildren().addAll(btnEdit, btnHelp);
            floatingMenu.setAlignment(Pos.CENTER_LEFT);
            floatingMenu.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-radius: 8; -fx-background-radius: 8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");

            // 添加到当前场景中
            Scene scene = btnSettings.getScene();
            AnchorPane root = (AnchorPane) scene.getRoot();
            root.getChildren().add(floatingMenu);

            // 定位菜单
            floatingMenu.setLayoutX(btnSettings.localToScene(0, 0).getX() + btnSettings.getWidth() - floatingMenu.getWidth() - 10);
            floatingMenu.setLayoutY(btnSettings.localToScene(0, 0).getY() + btnSettings.getHeight() + 5);

            // 点击其他地方关闭菜单
            scene.setOnMouseClicked(e -> {
                if (!btnSettings.getBoundsInParent().contains(e.getX(), e.getY())) {
                    hideFloatingMenu();
                }
            });
        } else {
            hideFloatingMenu();
        }
    }

    private void hideFloatingMenu() {
        if (floatingMenu != null) {
            ((AnchorPane) btnSettings.getScene().getRoot()).getChildren().remove(floatingMenu);
            floatingMenu = null;
        }
    }

}


