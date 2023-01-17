package com.example.cms9cc.admin.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.LoginBean;
import com.example.cms9cc.admin.mapper.LoginMapping;
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
    public String login(@RequestBody LoginBean requestLoginBean) {
        LoginBean loginBean = loginMapping.selectOne(new QueryWrapper<>());
        if (loginBean.equals(requestLoginBean)) {
            return JWTUtils.getToken(loginBean);
        }
        return "登录失败";
    }

    @PostMapping("/change_admin_info")
    public int changeAdminInfo(@RequestBody LoginBean requestLoginBean) {
        return loginMapping.update(requestLoginBean, new UpdateWrapper<>());
    }

    @GetMapping("/getinfo")
    @CrossOrigin
    public String getInfo() {
        return "{\"name\":\"admin\",\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"}";
    }

    @GetMapping("/getinfoadmin")
    @CrossOrigin
    public LoginBean getAdminInfo() {
        return loginMapping.selectOne(new QueryWrapper<>());
    }
}
