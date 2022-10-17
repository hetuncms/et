package com.example.cms9cc;

import com.example.cms9cc.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DemoEnvironment implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String name = "Config resource 'class path resource [application.properties]' via location 'optional:classpath:/'";
        MapPropertySource propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        Map<String, Object> source = propertySource.getSource();
        Map map = new HashMap();
        source.forEach((k, v) -> {
            map.put(k, v);
        });
        map.replace("spring.thymeleaf.prefix", "classpath:/templates/" + getTemplate(map.get("spring.datasource.url").toString()) + "/");

        environment.getPropertySources().replace(name, new MapPropertySource(name, map));
    }

    private String getTemplate(String sqlPath){
        try {
            Connection connection = DriverManager.getConnection(sqlPath);
            Statement statement = connection.createStatement();
            ResultSet select_current_template_from_basis = statement.executeQuery("select current_template from basis");
            while (select_current_template_from_basis.next()){
                String result =select_current_template_from_basis.getString(1);
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