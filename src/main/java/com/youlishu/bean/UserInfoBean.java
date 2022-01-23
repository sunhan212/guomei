package com.youlishu.bean;

import lombok.Data;

/**
 * @Author yujin
 * @Date 2021/2/3 16:39
 * @Version 1.0
 */
@Data
public class UserInfoBean {
    private String created_at;

    private String positions;

    private String name;
//    private String id;
    private String can_access;

    private String username;

    private String status;

    private String mobile;

    private String org;

    private String token;
}
