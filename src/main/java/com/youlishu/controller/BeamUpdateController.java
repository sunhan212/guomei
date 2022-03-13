package com.youlishu.controller;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamUpdateService;
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
 *@Description   梁板构件   编辑类
 *@Author   kd
 *@Date  2022/02/21 22:32
 *Exception
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/update")
@Api("梁板构件编辑类")
public class BeamUpdateController {

    @Autowired
    private BeamUpdateService beamUpdateService;
    /**
     *梁板构件   回显-----查询单条数据
     */
    @RequestMapping(value = "/findOneBeam", method = RequestMethod.GET)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "查看单条梁板构件数据", notes = "通过用户名和id查询单个梁板构件数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    public ResponseBean findOneBeam(@RequestParam(required = false)Integer id, HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            BeamTransformInfo beamTransformInfo = beamUpdateService.findOneBeam(id,username);
            if (!beamTransformInfo.equals(null)) {
                return new ResponseBean(200,"查询成功",beamTransformInfo);
            }else {
                return new ResponseBean(501, "查询失败", "null");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "代码错误", e.getMessage());
        }
    }

    /**
     *剪力墙---回显-----上传需要修改的参数
     */
    @RequestMapping(value = "/updateBeam",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传转换数据", notes = "上传转换梁板需要的图片及参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file1", value = "建筑空间png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "file2", value = "剪力墙png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "prjName", value = "建筑空间png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamDesignType", value = "梁构建设计方式", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLong", value = "连梁与框架梁分界线长度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamUp", value = "梁高上限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "beamLow", value = "梁高下限", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    public ResponseBean updateBeam(@RequestParam("file1") MultipartFile file1,
                                       @RequestParam("file2") MultipartFile file2,
                                       @RequestParam(required = false) String prjName,
                                       @RequestParam(required = false) Integer id,
                                       @RequestParam(required = false)String beamDesignType,
                                       @RequestParam(required = false)String beamLong,
                                       @RequestParam(required = false)String beamUp,
                                       @RequestParam(required = false)String beamLow, HttpServletRequest request){
        try {
            String username = request.getHeader("username");
            boolean a = beamUpdateService.updateBeam(file1,file2,prjName,id,beamDesignType,beamLong,beamUp,beamLow,username);
            if (a == true) {
                return new ResponseBean(200,"修改成功","成功");
            }else {
                return new ResponseBean(501, "修改失败", a);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(502, prjName+"修改失败", e.getMessage());
        }
    }

}
