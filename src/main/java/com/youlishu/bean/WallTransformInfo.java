package com.youlishu.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author yujin
 * @Date 2022/1/21 15:16
 * @Version 1.0
 */
@Data
public class WallTransformInfo {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 输入的txt参数地址
     */
    private String inTxtUrl;

    /**
     * 输入的png参数地址
     */
    private String inPngUrl;

    /**
     * 输出的txt参数
     */
    private String outTxtUrl;

    /**
     * 输出的png参数
     */
    private String outPngUrl;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 转换时间
     */
    private Date transformTime;

    /**
     * txt文件名称
     */
    private String txtFileName;

    /**
     * png文件名称
     */
    private String pngFileName;

    /**
     * 项目名称
     */
    private String prjName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInTxtUrl() {
        return inTxtUrl;
    }

    public void setInTxtUrl(String inTxtUrl) {
        this.inTxtUrl = inTxtUrl;
    }

    public String getInPngUrl() {
        return inPngUrl;
    }

    public void setInPngUrl(String inPngUrl) {
        this.inPngUrl = inPngUrl;
    }

    public String getOutTxtUrl() {
        return outTxtUrl;
    }

    public void setOutTxtUrl(String outTxtUrl) {
        this.outTxtUrl = outTxtUrl;
    }

    public String getOutPngUrl() {
        return outPngUrl;
    }

    public void setOutPngUrl(String outPngUrl) {
        this.outPngUrl = outPngUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }
}