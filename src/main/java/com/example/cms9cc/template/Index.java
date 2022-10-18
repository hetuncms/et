package com.example.cms9cc.template;


import com.example.cms9cc.LiveItem;
import com.example.cms9cc.admin.AdminService;
import com.example.cms9cc.template.bean.PaiHangBean;
import com.example.cms9cc.template.bean.PaiHangFromBean;
import com.example.cms9cc.tools.TemplateUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Index {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");
    public static final int TYPE_HOT = 0;
    public static final int TYPE_BASKETBALL = 2;
    public static final int TYPE_FOOTBALL = 1;
    public static final int TYPE_TIYU = 3;
    OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    AdminService adminService;

    @PostMapping("/loadmore")
    @org.springframework.web.bind.annotation.ResponseBody
    private String loadMore(HttpServletRequest request, HttpServletResponse response, @RequestHeader Map<String, String> header, @org.springframework.web.bind.annotation.RequestBody String reqBody) {
        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");

        LiveItem liveItem = requestData(header, reqBody);
        HashMap<String, Object> respData = new HashMap<>();
        respData.put("list", liveItem.getLive_item());

        WebContext context = new WebContext(request, response, request.getServletContext(), response.getLocale(), respData);
        String process = TemplateUtils.process("list_more.html", context);
        return process;
    }




    @GetMapping("/paihang")
    public String paihang(Model model){
        PaiHangBean paiHangBean = requestPaiHangData(8);
        model.addAttribute("items",paiHangBean.getItems());
        model.addAttribute("config",adminService.getAllConfig());
        return "paihang";
    }

    @GetMapping("/paihang_fragment")
    public String paihangFragment(Model model,@RequestParam("commid")Integer commid){
        PaiHangBean paiHangBean = requestPaiHangData(commid);
        model.addAttribute("items",paiHangBean.getItems());
        model.addAttribute("config",adminService.getAllConfig());
        return "paihang::content";
    }

    @GetMapping("/paihang_child_fragment")
    public String paihangChildFragment(Model model,@RequestParam("type")Integer type){
        PaiHangBean paiHangBean = requestPaiHangData(type);
//        model.addAttribute("items",paiHangBean.getItems());
//        model.addAttribute("config",adminService.getAllConfig());
        return "aaaaaaaaaaaaaaaaaaaaaa";
    }

    private PaiHangBean requestPaiHangData(Integer commid) {
        RequestBody body = RequestBody.create(JSON, "comid="+commid+"&type=1&catid=1");
        Map<String, String> header = new HashMap<>();
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        Headers headers = Headers.of(header);
        Request build = new Request.Builder().url("http://www.515.tv/paihang").headers(headers).post(body).build();

        String netData;
        try {
            Response execute = okHttpClient.newCall(build).execute();
            netData = execute.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        PaiHangBean paiHangBean = com.alibaba.fastjson2.JSON.parseObject(netData, PaiHangBean.class);
        return paiHangBean;
    }
    private LiveItem requestData(@RequestHeader Map<String, String> header, String requstbody) {
        RequestBody body = RequestBody.create(JSON, requstbody);
        Headers headers = Headers.of(header);
        System.out.println(requstbody);
        Request build = new Request.Builder().url("http://www.515.tv").headers(headers).post(body).build();

        Response execute;
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

    public String to(@RequestHeader Map<String, String> header, Integer listType, Model model) {
        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        String reqBody = "s=0&t=1&a=" + listType + "&g=1";
        LiveItem liveItem = requestData(header, reqBody);
        if (liveItem != null) {
            model.addAttribute("list", liveItem.getLive_item());
        }
        model.addAttribute("config", adminService.getAllConfig());
        model.addAttribute("listtype", listType);
        return "index";
    }
    @Value("${spring.thymeleaf.prefix}")
    private String templatePath;
    @GetMapping("/")
    public String index(@RequestHeader Map<String, String> header, Model model) {
        return to(header, TYPE_HOT, model);
    }

    @GetMapping("/football")
    public String football(@RequestHeader Map<String, String> header, Model model) {
        return to(header, TYPE_FOOTBALL, model);
    }

    @GetMapping("/basketball")
    public String basketball(@RequestHeader Map<String, String> header, Model model) {
        return to(header, TYPE_BASKETBALL, model);
    }

    @GetMapping("/tiyu")
    public String tiyu(@RequestHeader Map<String, String> header, Model model) {
        return to(header, TYPE_TIYU, model);
    }

    @GetMapping("/bofang")
    public String bofang(Model model, @RequestParam("iframelink") String iframelink) {
        model.addAttribute("iframelink", iframelink);
        model.addAttribute("config", adminService.getAllConfig());
        return "bofang";
    }
}