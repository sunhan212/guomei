package com.youlishu.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Description: TODO
 * @Author fengxian
 * @Date 2020/8/28
 * @Version V1.0
 **/
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}


