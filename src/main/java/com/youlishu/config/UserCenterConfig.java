package com.youlishu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 用户中心配置
 */
@ConfigurationProperties(prefix = "user-center")
@Component
@Data
public class UserCenterConfig {

    private String basePath;

    private String validateToken;

    private String getToken;

    private Map<String, String> getTokenParam;

    private String orgRootInfo;

    private String orgList;

    private String orgInfo;

    private String userList;

    private String userInfo;

    private String userAdd;

    private String userRole;

    private String userExternalInviteCode;

    private String userExternalAdd;

    private String authProcess;

}
