package com.example.cms9cc.admin.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.LoginBean;
import com.example.cms9cc.admin.mapper.LoginMapping;
import com.example.cms9cc.tools.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Resource
    LoginMapping loginMapping;

    @PostMapping("/login")
    public String login(@RequestBody LoginBean requestLoginBean) {
        LoginBean loginBean = loginMapping.selectOne(new QueryWrapper<>());
        if (loginBean.equals(requestLoginBean)) {
            return JWTUtils.getToken(loginBean);
        }
        return "";
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
