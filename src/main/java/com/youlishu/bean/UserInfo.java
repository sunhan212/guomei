package com.youlishu.bean;

/**@Author yujin
@Date 2022/1/19 13:58
@Version 1.0
*/

import lombok.Data;

/**
    * 用户
    */
@Data
public class UserInfo {
    /**
    * 用户id
    */
    private String id;

    /**
    * 账户id
    */
    private String accountId;

    private String extraId;

    /**
    * 头像地址
    */
    private String avatar;

    /**
    * 用户名
    */
    private String username;

    /**
    * 花名
    */
    private String nickname;

    /**
    * 用户来源	
    */
    private String source;

    /**
    * 姓名
    */
    private String name;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 用户类型	
    */
    private String userType;

    /**
    * 组织
    */
    private String org;

    /**
    * token
    */
    private String token;

    /**
    * 创建时间
    */
    private String createdAt;

    /**
    * 查看权限
    */
    private String authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getExtraId() {
        return extraId;
    }

    public void setExtraId(String extraId) {
        this.extraId = extraId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}