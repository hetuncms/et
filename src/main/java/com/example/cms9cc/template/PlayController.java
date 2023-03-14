package com.example.cms9cc.template;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/live")
public class PlayController {

    @GetMapping("/basketball/{id}")
    public String playBasketBall(HttpServletRequest request, Model model, @PathVariable("id") Integer id){
        model.addAttribute("requestUrl", request.getRequestURI());
        return "bofang";
    }

    public String playFootBall(@PathVariable Integer id){
        return "bofang";
    }
}
