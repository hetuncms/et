package com.example.cms9cc.tools.jsonparse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class PJson {
    public ObjectMapper getInstance() {
        return new ObjectMapper();
    }
}
