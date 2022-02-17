package com.youlishu.controller;

import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *@Description   梁-板构建上传图片以及TXT参数类
 *@Author   kd
 *@Date  2022/01/26 17:19
 *Exception
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/upload")
@Api("图片上传类")
public class BeamUploadController {

    @Autowired
    private BeamUploadService beamUploadService;

    /**
     * 上传需要转换的图片和txt参数
     * 从这开始
     */
    @RequestMapping(value = "/uploadBeam",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传转换数据", notes = "上传转换梁板需要的图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "建筑空间png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "file", value = "剪力墙png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "prjName", value = "建筑空间png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamDesignType", value = "梁构建设计方式", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLong", value = "连梁与框架梁分界线长度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamUp", value = "梁高上限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLow", value = "梁高下限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    private ResponseBean transformBeam(@RequestParam("file1") MultipartFile file1,
                                       @RequestParam("file2") MultipartFile file2,
                                       @RequestParam(required = false) String prjName,
                                       @RequestParam(required = false)String beamDesignType,
                                       @RequestParam(required = false)String beamLong,
                                       @RequestParam(required = false)String beamUp,
                                       @RequestParam(required = false)String beamLow, HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            int a = beamUploadService.transformBeam(file1,file2,prjName,username,beamDesignType,beamLong,beamUp,beamLow);
            if (a == 1) {
                return new ResponseBean(200,"上传成功",null);
            }else {
                return new ResponseBean(500, "上传失败", null);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "上传失败", e.getMessage());
        }
    }

    /**
     *@Description   上传剪力墙图片
     *@Author   kd
     *@Date  2022/02/11 17:21
     *Exception
     */
    @RequestMapping(value = "/uploadWall",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传转换数据", notes = "上传转换梁板需要的剪力墙图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file1", value = "剪力墙png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    private ResponseBean transformBeam(@RequestParam("file") MultipartFile file1,
                                       HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            int a = beamUploadService.uploadWall(file1,username);
            if (a == 1) {
                return new ResponseBean(200,"上传成功",null);
            }else {
                return new ResponseBean(500, "上传失败", null);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "上传失败", e.getMessage());
        }
    }

}
