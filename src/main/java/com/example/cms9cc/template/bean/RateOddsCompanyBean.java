
package com.example.cms9cc.template.bean;

import java.util.List;

public class RateOddsCompanyBean {

    private int code;
    private String message;
    private List<Result> result;
    private long time_stamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public class Result {

        private int id;
        private String name_zh;
        private String name_en;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName_zh() {
            return name_zh;
        }

        public void setName_zh(String name_zh) {
            this.name_zh = name_zh;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

    }
}
