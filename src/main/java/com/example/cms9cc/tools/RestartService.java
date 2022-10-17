package com.example.cms9cc.tools;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;

@Service
public class RestartService {

    @Resource
    private RestartEndpoint restartEndpoint;

    public void restartApp() {
        restartEndpoint.restart();
    }
}