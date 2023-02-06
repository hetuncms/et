package com.example.cms9cc.template;


import com.example.cms9cc.Config;
import com.example.cms9cc.LiveBean;
import com.example.cms9cc.admin.AdminService;
import com.example.cms9cc.template.bean.*;
import com.example.cms9cc.template.service.IndexService;
import com.example.cms9cc.tools.TemplateUtils;
import com.example.cms9cc.tools.jsonparse.PJson;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Controller
public class Index {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");
    public static final int TYPE_HOT = 0;
    public static final int TYPE_BASKETBALL = 2;
    public static final int TYPE_FOOTBALL = 1;
    public static final int TYPE_TIYU = 3;
    OkHttpClient okHttpClient = new OkHttpClient();
    @Autowired
    PJson pjson;
    AdminService adminService;
    IndexService indexService;
    Config config;
    @Value("${spring.thymeleaf.prefix}")
    private String templatePath;

    @Autowired
    public Index(AdminService adminService, Config config, IndexService indexService) {
        this.adminService = adminService;
        this.config = config;
        this.indexService = indexService;
    }

    @PostMapping("/loadmore")
    @org.springframework.web.bind.annotation.ResponseBody
    private String loadMore(HttpServletRequest request, HttpServletResponse response, @org.springframework.web.bind.annotation.RequestBody String reqBody) {
        LiveBean liveBean = requestData(reqBody);

        if (liveBean.getStatus() == -1 || liveBean.getLive_item() == null || liveBean.getLive_item().isEmpty()) {
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
        PaiHangBean paiHangBean;
        try {
            paiHangBean = pjson.getInstance().readValue(netData, PaiHangBean.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
    public String bofang(Model model, @PathVariable("id") Long id) {

        model.addAttribute("config", adminService.getAllConfig());

        PlayInfoBean liveInfo = indexService.getLiveInfo(id);

        MatchLeague footballLeague = liveInfo.getFootballLeague();

        model.addAttribute("footballLeague", footballLeague);

        PlayInfoBean.Result liveInfoBean = liveInfo.getLiveInfoBean();

        LiveBean.LiveItem liveItem = liveInfo.getData();

        String liveTypeText = switch (liveItem.getLiveType()) {
            case 1 -> "足球";
            case 2 -> "篮球";
            default -> "未知";
        };

        List<RateOddsItem> oddsItem = liveInfo.getOddsItem();
        if (oddsItem != null && !oddsItem.isEmpty()) {
            model.addAttribute("rateodds", oddsItem.get(0).getList().get(0));

            final String[] companyName = {"BET365"};
            final String finalCompanyName = "BET365";
            for (RateOddsItem rateOddsItem : oddsItem) {

                List<RateOddsItem.OddsItem> oddsItems = rateOddsItem.getList();
                RateOddsItem.OddsItem oddsrateItems = rateOddsItem.getList().stream().filter(new Predicate<RateOddsItem.OddsItem>() {
                    @Override
                    public boolean test(RateOddsItem.OddsItem oddsItem) {
                        return oddsItem.getCompanyName().startsWith(companyName[0]);
                    }
                }).findFirst().orElseGet(() -> {
                    RateOddsItem.OddsItem oddsItem1 = oddsItems.get(0);
                    companyName[0] = oddsItem1.getCompanyName();
                    return oddsItem1;
                });

                if (rateOddsItem.getType() == 1) {
                    // 欧指
                    model.addAttribute("ouzhi", oddsrateItems);
                } else if (rateOddsItem.getType() == 2) {
                    // 亚洲
                    model.addAttribute("yazhou", oddsrateItems);
                } else if (rateOddsItem.getType() == 3) {
                    // 大小盘
                    model.addAttribute("daxiaopan", oddsrateItems);
                }
            }
        }

        model.addAttribute("item", liveItem);
        model.addAttribute("liveTypeText", liveTypeText);
        // 如果infobean 没有说明没有播放链接，只返回比赛信息就可以
        if (liveInfoBean == null) {
            return "bofang";
        }
        List<PlayInfoBean.LiveStreams> liveStreams = liveInfoBean.getLive_streams();

        List<DPlayerQualityBean> collect = liveInfoBean.getLive_streams().stream().filter(new Predicate<PlayInfoBean.LiveStreams>() {
            @Override
            public boolean test(PlayInfoBean.LiveStreams liveStreams) {
                return liveStreams.getFormat() == 2;
            }
        }).map(new Function<PlayInfoBean.LiveStreams, DPlayerQualityBean>() {
            @Override
            public DPlayerQualityBean apply(PlayInfoBean.LiveStreams liveStreams) {
                DPlayerQualityBean dPlayerQualityBean = new DPlayerQualityBean();
                switch (liveStreams.getType()) {
                    case 1:
                        dPlayerQualityBean.setName("卫星/标清");
                        break;
                    case 2:
                        dPlayerQualityBean.setName("标清中文");
                        break;
                    case 3:
                        dPlayerQualityBean.setName("高清原声");
                        break;
                    case 4:
                        dPlayerQualityBean.setName("高清中文");
                        break;
                }

                switch (liveStreams.getFormat()) {
                    case 2:
                        dPlayerQualityBean.setType("application/x-mpegURL");
                        break;
                    case 3:
                        dPlayerQualityBean.setType("flv");
                }
                dPlayerQualityBean.setUrl(liveStreams.getUrl());
                return dPlayerQualityBean;
            }

        }).collect(Collectors.toList());

        if (collect != null) {
            model.addAttribute("playurl", collect);
            model.addAttribute("playUrls", liveStreams);
        }


        return "bofang";
    }
}
