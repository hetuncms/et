package com.example.cms9cc.admin.login;


import com.example.cms9cc.admin.bean.AdminUserBean;
import com.example.cms9cc.admin.bean.BaseAdminBean;
import com.example.cms9cc.admin.bean.LoginBean;
import com.example.cms9cc.admin.repositories.LoginMapping;
import com.example.cms9cc.tools.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class LoginController {

    LoginMapping loginMapping;

    @Autowired
    public LoginController(LoginMapping loginMapping) {
        this.loginMapping = loginMapping;
    }

    @PostMapping("/login")
    public BaseAdminBean<String> login(@RequestBody LoginBean requestLoginBean) {
        LoginBean loginBean = loginMapping.findAll().get(0);
        if (loginBean.equals(requestLoginBean)) {
            return new BaseAdminBean.Builder<String>().setCode(20000).setData(JWTUtils.getToken(loginBean)).build();
        }
        return new BaseAdminBean.Builder<String>().setCode(60204).setMessage("用户名或密码错误").build();
    }

    @PostMapping("/change_admin_info")
    public BaseAdminBean<LoginBean> changeAdminInfo(@RequestBody LoginBean requestLoginBean) {
        LoginBean save = loginMapping.save(requestLoginBean);
        return new BaseAdminBean.Builder<LoginBean>().buildSucces(save);
    }

    @GetMapping("/getinfo")
    @CrossOrigin
    public BaseAdminBean getInfo() {
        String body = "{\"name\":\"admin\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"}";
        AdminUserBean adminUserBean = new AdminUserBean();
        adminUserBean.setName("admin");
        adminUserBean.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new BaseAdminBean.Builder<AdminUserBean>().buildSucces(adminUserBean);
    }

    @PostMapping("/logout")
    @CrossOrigin
    public BaseAdminBean logout() {
        return new BaseAdminBean.Builder<String>().buildSucces("{\"name\":\"admin\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"}");
    }

    @GetMapping("/getinfoadmin")
    @CrossOrigin
    public BaseAdminBean<LoginBean> getAdminInfo() {
        LoginBean loginBean = loginMapping.findAll().get(0);
        return new BaseAdminBean.Builder<LoginBean>().buildSucces(loginBean);
    }
}
