package com.youlishu.bean;

import lombok.Data;

/**
 * @author TJ
 * @description TODO
 * @date 2020/12/13 16:35
 **/
@Data
public class LoginToken {

    private String token;

    private String authority;

    private String download_rights;

    private String upload_permission;
}
