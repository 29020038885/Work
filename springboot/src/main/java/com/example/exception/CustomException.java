package com.example.exception;

public class CustomException extends RuntimeException {
    private String msg;
    private String code;

    public CustomException(String code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
