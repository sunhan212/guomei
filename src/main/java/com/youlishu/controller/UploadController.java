package com.youlishu.controller;

import com.youlishu.bean.ResponseBean;
import com.youlishu.service.transformWallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
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
    @RequestMapping(value = "/inwall", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "上传转换数据", notes = "上传转换剪力墙需要的图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "seismic", value = "抗震设防烈度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "structure", value = "建筑结构高度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "scale", value = "比例尺", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "prjName", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    //就是这个接口   但是postman一直报token失效，我也没办法本地测试所以你先看看代码上有没有错误啊，别的字段都可以存进去，
    // 但是就是用户名这个额字段存不进去   用户名是从token里边取出来的   是不是因为token一直失效，然后就没有这个字段了啊    之前是可以存进去的，麻蛋的晚上更新了个包就不行了
    //从我们公司的一个产品里边获取的
    public ResponseBean transformWall(@RequestPart("file") MultipartFile file,
                                       @RequestParam(required = false)String seismic,
                                       @RequestParam(required = false)String structure,
                                       @RequestParam(required = false)String scale,
                                       @RequestParam(required = false)String prjName,HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            String a = transformWallService.transformwall(file,username,seismic,structure,scale,prjName);
            if ("1".equals(a)) {
                return new ResponseBean(200,"上传成功","成功");
            }else {
                return new ResponseBean(501, "数据上传失败", a);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, file.getOriginalFilename()+"文件上传失败", e.getMessage());
        }
    }
}
