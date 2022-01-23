package com.youlishu.controller;

import com.youlishu.bean.ResponseBean;
import com.youlishu.service.transformWallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author yujin
 * @Date 2022/1/19 18:31
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/upload")
@Api("转换类")
public class UploadController {

    @Autowired
    private transformWallService transformWallService;
    /**
     * 上传需要转换的图片和txt参数
     */
    @GetMapping("/inwall")
    @ApiOperation(value = "上传转换数据", notes = "上传转换剪力墙需要的图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "seismic", value = "抗震设防烈度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "structure", value = "建筑结构高度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "scale", value = "比例尺", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    private ResponseBean transformWall(@RequestParam("file") MultipartFile file,
                                       @RequestParam(required = false)String seismic,
                                       @RequestParam(required = false)String structure,
                                       @RequestParam(required = false)String scale,
                                       @RequestParam(required = false)String prjname,HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            int a = transformWallService.transformwall(file,username,seismic,structure,scale,prjname);
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
