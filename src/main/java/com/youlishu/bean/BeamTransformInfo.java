package com.youlishu.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeamTransformInfo implements Serializable {

    /**
     * 梁-板构建id
     */
    private Integer id;
    /**
     *项目名称
     */
    private String prjName;
    /**
     *项目状态
     */
    private String prjStatus;
    /**
     *剪力墙输出的png参数地址
     */
    private String wallOutPngUrl;
    /**
     *梁板构建输入的png参数地址
     */
    private String beamInPngUrl;
    /**
     *梁板构建输入的参数地址
     */
    private String beamInTxtUrl;
    /**
     *梁板构建输出的txt参数地址
     */
    private String beamOutTxtUrl;
    /**
     *板构建输出的png参数地址
     */
    private String beamOutPngUrl;
    /**
     *梁板构建上传时间
     */
    private Date beamUploadTime;
    /**
     *剪力墙上传时间
     */
    private Date wallUploadTime;
    /**
     *转换时间
     */
    private Date transformTime;
    /**
     *TXT文件名
     */
    private String txtFileName;
    /**
     *png文件名
     */
    private String pngFileName;
    /**
     *用户名
     */
    private String userName;
    /**
     *用户名
     */
    private String beamDesignType;
    /**
     *用户名
     */
    private String beamLong;
    /**
     *用户名
     */
    private String beamUp;
    /**
     *用户名
     */
    private String beamLow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjStatus() {
        return prjStatus;
    }

    public void setPrjStatus(String prjStatus) {
        this.prjStatus = prjStatus;
    }

    public String getWallOutPngUrl() {
        return wallOutPngUrl;
    }

    public void setWallOutPngUrl(String wallOutPngUrl) {
        this.wallOutPngUrl = wallOutPngUrl;
    }

    public String getBeamInPngUrl() {
        return beamInPngUrl;
    }

    public void setBeamInPngUrl(String beamInPngUrl) {
        this.beamInPngUrl = beamInPngUrl;
    }

    public String getBeamInTxtUrl() {
        return beamInTxtUrl;
    }

    public void setBeamInTxtUrl(String beamInTxtUrl) {
        this.beamInTxtUrl = beamInTxtUrl;
    }

    public String getBeamOutTxtUrl() {
        return beamOutTxtUrl;
    }

    public void setBeamOutTxtUrl(String beamOutTxtUrl) {
        this.beamOutTxtUrl = beamOutTxtUrl;
    }

    public String getBeamOutPngUrl() {
        return beamOutPngUrl;
    }

    public void setBeamOutPngUrl(String beamOutPngUrl) {
        this.beamOutPngUrl = beamOutPngUrl;
    }

    public Date getBeamUploadTime() {
        return beamUploadTime;
    }

    public void setBeamUploadTime(Date beamUploadTime) {
        this.beamUploadTime = beamUploadTime;
    }

    public Date getWallUploadTime() {
        return wallUploadTime;
    }

    public void setWallUploadTime(Date wallUploadTime) {
        this.wallUploadTime = wallUploadTime;
    }

    public Date getTransformTime() {
        return transformTime;
    }

    public void setTransformTime(Date transformTime) {
        this.transformTime = transformTime;
    }

    public String getTxtFileName() {
        return txtFileName;
    }

    public void setTxtFileName(String txtFileName) {
        this.txtFileName = txtFileName;
    }

    public String getPngFileName() {
        return pngFileName;
    }

    public void setPngFileName(String pngFileName) {
        this.pngFileName = pngFileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBeamDesignType() {
        return beamDesignType;
    }

    public void setBeamDesignType(String beamDesignType) {
        this.beamDesignType = beamDesignType;
    }

    public String getBeamLong() {
        return beamLong;
    }

    public void setBeamLong(String beamLong) {
        this.beamLong = beamLong;
    }

    public String getBeamUp() {
        return beamUp;
    }

    public void setBeamUp(String beamUp) {
        this.beamUp = beamUp;
    }

    public String getBeamLow() {
        return beamLow;
    }

    public void setBeamLow(String beamLow) {
        this.beamLow = beamLow;
    }
}
