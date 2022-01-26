package com.youlishu.controller;

import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @GetMapping("/uploadBeam")
    @ApiOperation(value = "上传转换数据", notes = "上传转换梁板需要的图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamDesignType", value = "梁构建设计方式", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLong", value = "连梁与框架梁分界线长度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamUp", value = "梁高上限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLow", value = "梁高下限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    private ResponseBean transformBeam(@RequestParam("file") MultipartFile file,
                                       @RequestParam(required = false)String beamDesignType,
                                       @RequestParam(required = false)String beamLong,
                                       @RequestParam(required = false)String beamUp,
                                       @RequestParam(required = false)String beamLow,
                                       @RequestParam(required = false)String prjname, HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            int a = beamUploadService.transformBeam(file,username,beamDesignType,beamLong,beamUp,beamLow,prjname);
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
     *@Description  上传剪力墙图片
     *@Author   kd
     *@Date  2022/01/26 19:01
     *Exception
     *
     */
    @GetMapping("/uploadWall")
    private ResponseBean uploadWall(@RequestParam("file") MultipartFile file,
                                    HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            int a = beamUploadService.uploadWall(file,username);
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
