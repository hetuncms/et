package com.example.cms9cc.template;

import com.example.cms9cc.admin.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/live")
public class PlayController {

    AdminService adminService;

    public PlayController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/basketball/{id}")
    public String playBasketBall(HttpServletRequest request, Model model, @PathVariable("id") Integer id){
        model.addAttribute("requestUrl", request.getRequestURI());
        getBaseModel(model);
        return "bofang";
    }

    public String playFootBall(@PathVariable Integer id){
        return "bofang";
    }

    public Model getBaseModel(Model model) {
        model.addAttribute("config", adminService.getAllConfig());
        return model;
    }
}
