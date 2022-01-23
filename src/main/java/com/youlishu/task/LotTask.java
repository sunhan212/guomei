//package com.lot.task;
//
//import com.lot.service.DataAccessService;
//import com.lot.service.SystemService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
///**
// * @Author yujin
// * @Date 2020/11/9 11:25
// * @Version 1.0
// */
//@Component
//public class LotTask {
//
//    @Autowired
//    private DataAccessService dataAccessService;
//
//    @Autowired
//    private SystemService systemService;
//
//    private static final Log log = LogFactory.getLog(LotTask.class);
//
//    /**
//     * 同步扬尘设备和数据信息 10m
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    public void dust() {
//        int re = dataAccessService.dustData();
//        if (re == 1) {
//            log.info("同步扬尘设备信息成功" + new Date());
//        } else {
//            log.info("同步扬尘设备信息失败" + new Date());
//        }
//    }
//    /**
//     * 同步污染源设备和数据信息 19m
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    public void pollution() {
//        int re = dataAccessService.pollutionSources();
//        if (re == 1) {
//            log.info("同步污染源信息成功" + new Date());
//        } else {
//            log.info("同步污染源信息失败" + new Date());
//        }
//    }
//    /**
//     * 同步出租车走航
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 30 0/1 * * ?")
//    public void taxi() {
//        int re = dataAccessService.taxi();
//        if (re == 1) {
//            log.info("同步出租车走航信息成功" + new Date());
//        } else {
//            log.info("同步出租车走航信息失败" + new Date());
//        }
//    }
//    /**
//     * 同步机动车遥感监测 22s
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 35 0/1 * * ?")
//    public void yaogan() {
//        int re = dataAccessService.yaogan();
//        if (re == 1) {
//            log.info("同步机动车遥感监测信息成功" + new Date());
//        } else {
//            log.info("同步机动车遥感监测信息失败" + new Date());
//        }
//    }
//    /**
//     * 同步空气质量监测 14m
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 37 0/1 * * ?")
//    public void airQuality() {
//        int re = dataAccessService.airQuality();
//        if (re == 1) {
//            log.info("同步空气质量监测信息成功" + new Date());
//        } else {
//            log.info("同步空气质量监测信息失败" + new Date());
//        }
//    }
//    /**
//     * 同步水环境监测 3m
//     * 定时任务一小时
//     */
//    @Scheduled(cron = "0 55 0/1 * * ?")
//    public void waterEnvironment() {
//        int re = dataAccessService.waterEnvironment();
//        if (re == 1) {
//            log.info("同步水环境监测信息成功" + new Date());
//        } else {
//            log.info("同步水环境监测信息失败" + new Date());
//        }
//    }
//    /**
//     * 添加系统资源信息
//     * 五分钟
//     */
//    @Scheduled(cron = "0 0/5 * * * ? ")
//    public void system() throws Exception {
//        int re = systemService.resourceData();
//        if (re == 1) {
//            log.info("添加系统资源成功" + new Date());
//        } else {
//            log.info("添加系统资源失败" + new Date());
//        }
//    }
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void vehicleStatusInfo() {
//        int re = dataAccessService.VehicleStatusInfo();
//        if (re == 1) {
//            log.info("渣土车实时数据同步成功" + new Date());
//        } else {
//            log.info("渣土车实时数据同步失败" + new Date());
//        }
//    }
//    @Scheduled(cron = "0 0/10 * * * ? ")
//    public void vehicle() {
//        int re = dataAccessService.vehicel();
//        if (re == 1) {
//            log.info("渣土车实时数据处理入库更新成功" + new Date());
//        } else {
//            log.info("渣土车实时数据处理入库更新失败" + new Date());
//        }
//    }
//
//    /**
//     * 定时任务清空lot_data表
//     * 每月底清空一次
//     * 每月最后一天凌晨六点
//     */
//    @Scheduled(cron = "0 0 6 1 1/1 ?")
//    public void deletelot_data(){
//        int re = dataAccessService.deletelot_data();
//        if (re == 1) {
//            log.info("清空lot_data表成功" + new Date());
//        } else {
//            log.info("清空lot_data表失败" + new Date());
//        }
//    }
//    /**
//     * 定时任务清空vehicle_status_Info表
//     * 每月初清空一次
//     * 每月第一天凌晨六点
//     */
//    @Scheduled(cron = "0 0 6 1 1/1 ?")
//    public void deletevehicle_status_Info(){
//        int re = dataAccessService.deletevehicle_status_Info();
//        if (re == 1) {
//            log.info("清空vehicle_status_Info表成功" + new Date());
//        } else {
//            log.info("清空vehicle_status_Info表失败" + new Date());
//        }
//    }
//
//    /**
//     * 定时任务清空lot_data_vehicle表
//     * 每三天一次
//     * 凌晨六点
//     */
//        @Scheduled(cron = "0 0 6 1/3 * ?")
//    public void deletelot_data_vehicle(){
//        int re = dataAccessService.deletelot_data_vehicle();
//        if (re == 1) {
//            log.info("清空lot_data_vehicle表成功" + new Date());
//        } else {
//            log.info("清空lot_data_vehicle表失败" + new Date());
//        }
//    }
//
//    /**
//     * 定时任务清空lot_device_information_vchicle表
//     * 每三天一次
//     * 凌晨六点
//     */
//    @Scheduled(cron = "0 0 6 1/3 * ?")
//    public void deletelot_device_information_vchicle(){
//        int re = dataAccessService.deletelot_device_information_vchicle();
//        if (re == 1) {
//            log.info("清空lot_device_information_vchicle表成功" + new Date());
//        } else {
//            log.info("清空lot_device_information_vchicle表失败" + new Date());
//        }
//    }
//    /**
//     * 定时任务清空lot_data_pollution表
//     * 每三天一次
//     * 凌晨六点
//     */
//    @Scheduled(cron = "0 0 6 1/3 * ?")
//    public void deletelot_data_pollution(){
//        int re = dataAccessService.deletelot_data_pollution();
//        if (re == 1) {
//            log.info("清空lot_data_pollution表成功" + new Date());
//        } else {
//            log.info("清空lot_data_pollution表失败" + new Date());
//        }
//    }
//    /**
//     * 判断预警级别
//     * 定时任务四小时
//     */
//    @Scheduled(cron = "0 0 0/4 * * ?")
//    public void level() {
//        int re = dataAccessService.wuran_level();
//        if (re == 1) {
//            log.info("判断污染级别成功" + new Date());
//        } else {
//            log.info("判断污染级别失败" + new Date());
//        }
//    }
//    /**
//     * 今日新增数据量
//     * 每日早九点
//     */
//    @Scheduled(cron = "0 0 9 * * ?")
//    public void dataSize() {
//        int re = dataAccessService.dataSize();
//        if (re == 1) {
//            log.info("判断今日新增数据量成功" + new Date());
//        } else {
//            log.info("判断今日新增数据量失败" + new Date());
//        }
//    }
//}
