package com.example.cms9cc.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

public class TemplateUtils {
    @Autowired
    public static final TemplateEngine engine = ApplicationContextHolder.getBean(TemplateEngine.class);

    public static String process(String freeTempName, WebContext context) {
        return engine.process(freeTempName, context);
    }
}