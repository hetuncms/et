package com.example.cms9cc.admin.bean;

public class BaseAdminBean<T> {
    public int code;
    public String message;
    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseAdminBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseAdminBean(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

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

    public static class Builder<BT> {
        public int code;
        public String message;

        public BT data;

        public BT getData() {
            return data;
        }

        public Builder<BT> setData(BT data) {
            this.data = data;
            return this;
        }

        public Builder<BT> setCode(int code) {
            this.code = code;
            return this;
        }

        public Builder<BT> setMessage(String msg) {
            this.message = msg;
            return this;
        }

        public BaseAdminBean<BT> build() {
            return new BaseAdminBean<BT>(this);
        }
        public BaseAdminBean<BT> buildSucces(BT data) {
            setCode(20000);
            setMessage("succes");
            setData(data);
            return new BaseAdminBean<BT>(this);
        }
        public BaseAdminBean<BT> buildSucces() {
            setCode(20000);
            setMessage("succes");
            return new BaseAdminBean<BT>(this);
        }
    }
}
