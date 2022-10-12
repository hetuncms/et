package com.example.cms9cc.template;


import com.example.cms9cc.LiveItem;
import com.example.cms9cc.admin.AdminService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class Index {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");

    OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    AdminService adminService;

    private LiveItem requestData(@RequestHeader Map<String, String> header, String requstbody) {
        RequestBody body = RequestBody.create(JSON, requstbody);
        Headers headers = Headers.of(header);
        System.out.println(requstbody);
        Request build = new Request.Builder().url("http://www.515.tv").headers(headers).post(body).build();

        Response execute = null;
        String netData;
        try {
            execute = okHttpClient.newCall(build).execute();
            netData = execute.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LiveItem liveset = com.alibaba.fastjson2.JSON.parseObject(netData, LiveItem.class);
        if (liveset == null) {
            return null;
        }
        List<LiveItem.Item> live_item = liveset.getLive_item();
        if (live_item == null || liveset.getInfo() != null) {
            if (liveset.getInfo() == null || liveset.getInfo().isEmpty()) {
                liveset.setInfo("未知错误");
            }
            liveset.setStatus(-1);
            return null;
        }

        live_item.forEach(item -> {
            item.setIframeLink("http://www.515.tv/live/" + item.getPlayid() + "?rel=0&amp&autoplay=1");
            LiveItem.RelationT relationT = liveset.getT().get(item.getId());

            if (relationT != null) {
                item.setMatchId(relationT.getI());
                item.setLid(relationT.getL());
            }

            LiveItem.RelationA leftRelationA = liveset.getA().get(item.getI());

            if (leftRelationA != null) {
                item.setLeftImg(leftRelationA.getN());
                item.setLeftName(leftRelationA.getI());
            }

            LiveItem.RelationA rightRelationA = liveset.getA().get(item.getC());

            if (rightRelationA != null) {
                item.setRightName(rightRelationA.getI());
                item.setRightImg(rightRelationA.getN());
            }

            LiveItem.RelationO relationO = liveset.getO().get(item.getH());

            if (relationO != null) {
                item.setGameName(relationO.getI());
            }
        });


        return liveset;
    }
    public String to(@RequestHeader Map<String, String> header,String reqBody, Model model){
        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        LiveItem liveItem = requestData(header, reqBody);
        if (liveItem != null) {
            model.addAttribute("list",liveItem.getLive_item());
        }
        model.addAttribute("config",adminService.getAllConfig());
        return "index";
    }

    @GetMapping("/")
    public String index(@RequestHeader Map<String, String> header, Model model) {
        String reqBody = "s=0&t=1&a=0&g=1";
        return to(header,reqBody,model);
    }
    @GetMapping("/football")
    public String football(@RequestHeader Map<String, String> header, Model model) {
        String reqBody = "s=0&t=1&a=1&g=1";
        return to(header,reqBody,model);
    }

    @GetMapping("/basketball")
    public String basketball(@RequestHeader Map<String, String> header, Model model) {
        String reqBody = "s=0&t=1&a=2&g=1";
        return to(header,reqBody,model);
    }

    @GetMapping("/tiyu")
    public String tiyu(@RequestHeader Map<String, String> header, Model model) {
        String reqBody = "s=0&t=1&a=3&g=1";
        return to(header,reqBody,model);
    }
    @GetMapping("/bofang")
    public String bofang(Model model, @RequestParam("iframelink") String iframelink) {
        model.addAttribute("iframelink",iframelink);
        return "bofang";
    }
}