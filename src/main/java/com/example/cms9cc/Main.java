package com.example.cms9cc;


import com.example.cms9cc.tools.Zip;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Main {
    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;");

    OkHttpClient okHttpClient = new OkHttpClient();
    private String resPath = "";

    {
        resPath = this.getClass().getResource("/static").getPath();
    }

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

        LiveItem liveset = (LiveItem) com.alibaba.fastjson2.JSON.parseObject(netData, LiveItem.class);
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

    private List<CmsPlayBean> toCmsBean(Map<String, String> map, String requstbody) {
        ArrayList<CmsPlayBean> cmsPlayBeans = new ArrayList<>();
        List<LiveItem.Item> live_item = requestData(map, requstbody).getLive_item();
        live_item.forEach(item -> {
            CmsPlayBean cmsPlayBean = new CmsPlayBean(item.getId(), item.getGameType(),
                    item.getLeftName() + "=" + item.getRightName(), item.getLeftImg(), item.getIframeLink(), item.getDate());
            cmsPlayBeans.add(cmsPlayBean);
        });
        return cmsPlayBeans;
    }

    @PostMapping("/index")
    public LiveItem index(@RequestHeader Map<String, String> header, @RequestBody String reqBody) {

        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        LiveItem liveItem = requestData(header, reqBody);
        return liveItem;
    }

    @GetMapping({"/download"})
    @CrossOrigin
    public String download(@RequestHeader Map<String, String> header) {

        header.remove("accept-encoding");
        header.put("origin", "http://www.515.tv");
        header.put("host", "www.515.tv");
        header.put("X-Requested-With", "XMLHttpRequest");
        Headers headers = Headers.of(header);

        for (int i = 0; i < 3; i++) {
            String requestBody = "s=0&t=1&a=" + i;
            int page = 1;

            System.out.println("下载" + i + "==============");
            ArrayList<CmsPlayBean> cmsPlayBeans = new ArrayList<>();

            while (true) {
                List<CmsPlayBean> cmsPlayList = toCmsBean(header, requestBody + "&g=" + page);
                if (cmsPlayList == null) {
                    break;
                }
                cmsPlayBeans.addAll(cmsPlayList);
                System.out.println("下载" + i + "===g:" + page);
                page++;
            }
            String filename = String.valueOf(i + 1);
            writeFile(filename, com.alibaba.fastjson2.JSON.toJSONString(cmsPlayBeans));
            try {
                Zip.zipFile(resPath + File.separator + filename, resPath + File.separator + filename + ".zip");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    private void writeFile(String fileName, String fileContent) {
        File dir = new File(resPath);
        File file = new File(dir, fileName);
        //文件是否存在，不存在就创建
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //获取sink对象
            Sink write = Okio.sink(file);
            //获取sink缓冲对象
            BufferedSink bufferedSink = Okio.buffer(write);
            //写入数据
            bufferedSink.writeUtf8(fileContent);
            //关闭sink
            bufferedSink.close();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin
    @GetMapping("getcmsinfo")
    public CmsInfoBean getCmsInfo() {
        CmsInfoBean cmsInfoBean = new CmsInfoBean();

        ArrayList<String> zip = new ArrayList<>();
        File file = new File(resPath);
        for (File listFile : file.listFiles()) {

            String name = listFile.getName();
            if (name.endsWith(".zip")) {
                zip.add(name);
            }
        }

        cmsInfoBean.setZip(zip);
        return cmsInfoBean;
    }
}