package com.example.cms9cc.admin.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.cms9cc.admin.bean.LoginBean;
import com.example.cms9cc.admin.mapper.LoginMapping;
import com.example.cms9cc.tools.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Resource
    LoginMapping loginMapping;
    @PostMapping("/login")
    public String login( LoginBean requestLoginBean){
        LoginBean loginBean = loginMapping.selectOne(new QueryWrapper<>());

        if (loginBean.equals(requestLoginBean)) {
            return JWTUtils.getToken(loginBean);
        }
        return "";
    }

    @PostMapping("/change_admin_info")
    public int changeAdminInfo(@RequestBody LoginBean requestLoginBean){
        int update = loginMapping.update(requestLoginBean, new UpdateWrapper<>());
        return update;
    }
}
