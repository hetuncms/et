package com.example.cms9cc;

import com.example.cms9cc.tools.mobile.device.DeviceHandlerMethodArgumentResolver;
import com.example.cms9cc.tools.mobile.device.DeviceResolverHandlerInterceptor;
import com.example.cms9cc.tools.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.MimeType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.WebContentGenerator;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override // org.springframework.web.servlet.config.annotation.WebMvcConfigurer
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").
                allowedMethods(WebContentGenerator.METHOD_GET,
                        WebContentGenerator.METHOD_HEAD,
                        WebContentGenerator.METHOD_POST,
                        "PUT", "DELETE",
                        "OPTIONS")
                .allowCredentials(true).maxAge(3600L);

    }
    private String appendCharset(MimeType type, String charset) {
        if (type.getCharset() != null) {
            return type.toString();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("charset", charset);
        parameters.putAll(type.getParameters());
        return new MimeType(type, parameters).toString();
    }

    @Bean
    public LiteDeviceDelegatingViewResolver thymeleafViewResolver(ThymeleafProperties properties, SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding(properties.getEncoding().name());
        resolver.setContentType(appendCharset(properties.getServlet().getContentType(), resolver.getCharacterEncoding()));
        resolver.setProducePartialOutputWhileProcessing(properties.getServlet().isProducePartialOutputWhileProcessing());
        resolver.setExcludedViewNames(properties.getExcludedViewNames());
        resolver.setViewNames(properties.getViewNames());
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
        resolver.setCache(properties.isCache());

        LiteDeviceDelegatingViewResolver delegate = new LiteDeviceDelegatingViewResolver(resolver);
        delegate.setMobilePrefix("mobile/");
        delegate.setTabletPrefix("tablet/");
        delegate.setEnableFallback(true);
        return delegate;
    }
}
