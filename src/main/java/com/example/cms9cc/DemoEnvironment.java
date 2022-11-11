package com.example.cms9cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DemoEnvironment implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String name = "Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'";
        String onlineName = "Config resource 'file [application.properties]' via location 'optional:file:./'";
        MapPropertySource propertySource;
        propertySource = (MapPropertySource) environment.getPropertySources().get(onlineName);

        if (propertySource == null) {
            propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        } else {
            name = onlineName;
        }

        Map<String, Object> source = propertySource.getSource();
        Map map = new HashMap();
        source.forEach((k, v) -> {
            map.put(k, v);
        });
        String templatePath = map.get("template.path").toString();
        map.replace("spring.thymeleaf.prefix", templatePath + getTemplate(map.get("spring.datasource.url").toString()) + File.separator);
        System.out.println("===============" + map.get("spring.thymeleaf.prefix"));
        environment.getPropertySources().replace(name, new MapPropertySource(name, map));
    }

    private String getTemplate(String sqlPath) {
        try {
            Connection connection = DriverManager.getConnection(sqlPath);
            Statement statement = connection.createStatement();
            ResultSet select_current_template_from_basis = statement.executeQuery("select current_template from basis");
            while (select_current_template_from_basis.next()) {
                String result = select_current_template_from_basis.getString(1);
                statement.close();
                connection.close();
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
