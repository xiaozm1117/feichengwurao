package com.xiao;

public class Result {
    private Object object;
    private String msg;
    private int status=400;

    public Result(String s) {
        this.msg=s;
        if(s=="ok"){
            this.status=200;}else {
            this.status=400;}
    }

    public Result(Object obj) {
        this.object=obj;
        this.msg="ok";
        this.status=200;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
