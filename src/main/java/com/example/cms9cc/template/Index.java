package com.example.cms9cc.template;


import com.example.cms9cc.Config;
import com.example.cms9cc.LiveBean;
import com.example.cms9cc.admin.AdminService;
import com.example.cms9cc.template.bean.PaiHangBean;
import com.example.cms9cc.template.service.IndexService;
import com.example.cms9cc.tools.TemplateUtils;
import okhttp3.RequestBody;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Index {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");
    public static final int TYPE_HOT = 0;
    public static final int TYPE_BASKETBALL = 2;
    public static final int TYPE_FOOTBALL = 1;
    public static final int TYPE_TIYU = 3;
    OkHttpClient okHttpClient = new OkHttpClient();

    AdminService adminService;
    @Value("${spring.thymeleaf.prefix}")
    private String templatePath;


    IndexService indexService;
    Config config;

    @Autowired
    public Index(AdminService adminService, Config config, IndexService indexService) {
        this.adminService = adminService;
        this.config = config;
        this.indexService = indexService;
    }

    @PostMapping("/loadmore")
    @org.springframework.web.bind.annotation.ResponseBody
    private String loadMore(HttpServletRequest request, HttpServletResponse response,
                            @org.springframework.web.bind.annotation.RequestBody String reqBody) {
        LiveBean liveBean = requestData(reqBody);

        if (liveBean.getStatus() == -1 || liveBean.getLive_item()==null ||liveBean.getLive_item().isEmpty()) {
            return "";
        }

        HashMap<String, Object> respData = new HashMap<>();
        respData.put("list", liveBean.getLive_item());

        WebContext context = new WebContext(request, response, request.getServletContext(), response.getLocale(), respData);
        String process = TemplateUtils.process("list_more.html", context);
        return process;
    }

    @GetMapping("/paihang")
    public String paihang(Model model) {
        PaiHangBean paiHangBean = requestPaiHangData(8);
        model.addAttribute("items", paiHangBean.getItems());
        model.addAttribute("config", adminService.getAllConfig());
        return "paihang";
    }

    @GetMapping("/paihang_fragment")
    public String paihangFragment(Model model, @RequestParam("commid") Integer commid) {
        PaiHangBean paiHangBean = requestPaiHangData(commid);
        model.addAttribute("items", paiHangBean.getItems());
        model.addAttribute("config", adminService.getAllConfig());
        return "paihang::content";
    }

    @GetMapping("/paihang_child_fragment")
    public String paihangChildFragment(Model model, @RequestParam("type") Integer type) {
        PaiHangBean paiHangBean = requestPaiHangData(type);
//        model.addAttribute("items",paiHangBean.getItems());
//        model.addAttribute("config",adminService.getAllConfig());
        return "aaaaaaaaaaaaaaaaaaaaaa";
    }

    private PaiHangBean requestPaiHangData(Integer commid) {
        RequestBody body = RequestBody.create(JSON, "comid=" + commid + "&type=1&catid=1");
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

    private LiveBean requestData(String requstbody) {
        return indexService.index(requstbody);
    }

    private String requestM3u8Url(String id) {
        return indexService.getIframeLinkByid(id);
    }

    public String to(Integer listType, Model model) {
        String reqBody = "s=0&t=1&a=" + listType + "&g=0";
        LiveBean liveBean = requestData(reqBody);
        if (liveBean != null) {
            model.addAttribute("list", liveBean.getLive_item());
        }
        model.addAttribute("config", adminService.getAllConfig());
        model.addAttribute("listtype", listType);
        return "index";
    }

    @GetMapping("/")
    public String index(@RequestHeader Map<String, String> header, Model model) {
        return to(TYPE_HOT, model);
    }

    @GetMapping("/football")
    public String football(@RequestHeader Map<String, String> header, Model model) {
        return to(TYPE_FOOTBALL, model);
    }

    @GetMapping("/basketball")
    public String basketball(@RequestHeader Map<String, String> header, Model model) {
        return to(TYPE_BASKETBALL, model);
    }

    @GetMapping("/tiyu")
    public String tiyu(@RequestHeader Map<String, String> header, Model model) {
        return to(TYPE_TIYU, model);
    }


    @GetMapping("/bofang/{id}")
    public String bofang(Model model, @PathVariable("id") Long  id) {

        model.addAttribute("item", indexService.getLiveItem(id));
        model.addAttribute("config", adminService.getAllConfig());
//        model.addAttribute("item", item);

        return "bofang";
    }
}
