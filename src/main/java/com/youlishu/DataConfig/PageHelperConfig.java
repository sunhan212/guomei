package com.youlishu.DataConfig;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @Author yujin
 * @Date 2020/11/11 20:01
 * @Version 1.0
 * 分页
 */
public class PageHelperConfig {
    @Bean//加上该注解spring容器自动配置
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
