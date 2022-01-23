package com.youlishu.bean;

import lombok.Data;

/**
 * @Author yujin
 * @Date 2020/11/11 20:15
 * @Version 1.0
 */
@Data
public class ResponseBean {
    private Integer status;

    private String msg;

    private Object data;

    public static ResponseBean build() {
        return new ResponseBean();
    }

    public static ResponseBean ok(String msg) {
        return new ResponseBean(200, msg, null);
    }

    public static ResponseBean ok(String msg, Object obj) {
        return new ResponseBean(200, msg, obj);
    }

    public static ResponseBean error(String msg) {
        return new ResponseBean(500, msg, null);
    }

    public static ResponseBean error(String msg, Object obj) {
        return new ResponseBean(500, msg, obj);
    }

    private ResponseBean() {

    }

    public ResponseBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.data = obj;
    }

}
