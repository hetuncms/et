package com.example.cms9cc.config;

import com.example.cms9cc.tools.mobile.device.switcher.SiteSwitcherHandler;
import com.example.cms9cc.tools.mobile.device.switcher.annotation.SiteSwitcherConfigurer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class TemplatesConfiguration implements SiteSwitcherConfigurer {
    @Override
    public SiteSwitcherHandler getSiteSwitcherHandler() {
        return new SiteSwitcherHandler() {
            @Override
            public boolean handleSiteSwitch(HttpServletRequest request, HttpServletResponse response) throws IOException {
                return true;
            }
        };
    }
}
