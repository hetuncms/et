package com.example.cms9cc;


import com.example.cms9cc.admin.AdminService;
import com.example.cms9cc.admin.bean.AllAdBean;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Main {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");

    OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    AdminService adminService;

    private LiveItem requestData(@RequestHeader Map<String, String> header, String requstbody) {
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, requstbody);
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

    @PostMapping("/index")
    public LiveItem index(@RequestHeader Map<String, String> header, @org.springframework.web.bind.annotation.RequestBody String reqBody) {
        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        LiveItem liveItem = requestData(header, reqBody);

        return liveItem;
    }


    @GetMapping("/getconfig")
    public AllAdBean getConfig() {
        return adminService.getAllConfig();
    }
}