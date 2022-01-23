package com.youlishu.util;

import com.hikvision.artemis.sdk.config.ArtemisConfig;

/**
 * @Description: TODO
 * @Author fengxian
 * @Date 2020/8/6
 * @Version V1.0
 **/
public class Config {
    /**
     * 能力开放平台的网站路径
     * TODO 路径不用修改，就是/artemis
     */
    private static final String ARTEMIS_PATH = "/artemis";

    public static String getArtemisPath() {
        return ARTEMIS_PATH;
    }

    static {
        ArtemisConfig.host = "10.166.61.2:443"; // 代理API网关nginx服务器ip端口
        ArtemisConfig.appKey = "24416703";  // 秘钥appkey
        ArtemisConfig.appSecret = "94BzGCoEbM0wu7thXoVy";// 秘钥appSecret
    }
    }
