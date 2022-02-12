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

}
