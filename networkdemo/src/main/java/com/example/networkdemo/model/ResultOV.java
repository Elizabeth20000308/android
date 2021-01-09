package com.example.networkdemo.model;

public class ResultOV {
    private String result;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public ResultOV(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ResultOV(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
