package com.youlishu.bean;

import lombok.Data;

import java.util.Date;

/**@Author yujin
@Date 2022/1/20 11:17
@Version 1.0
*/
/**
    * 日志
    */
@Data
public class UserLog {
    /**
    * id
    */
    private Integer id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 访问时间
    */
    private Date time;

    /**
    * 操作内容
    */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}