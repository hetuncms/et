package com.example.cms9cc.admin.bean;

public class ResultBean {

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean() {
    }

    public ResultBean(Integer code) {
        this.code = code;
    }

    public ResultBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static class Builder{
        public ResultBean build(){
            return new ResultBean();
        }
        public ResultBean buildSucces(int code,String msg){
            return new ResultBean(code,msg);
        }
        public ResultBean buildSucces(int code){
            return new ResultBean(code);
        }
        public ResultBean buildSucces(){
            return buildSucces(20000,"");
        }
    }
}
