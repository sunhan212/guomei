package com.youlishu.controller;


import com.youlishu.bean.ResponseBean;
import com.youlishu.bean.WallTransformInfo;
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
 *@Description    剪力墙编辑类
 *@Author   shier
 *@Date  2022/02/18 09:34
 *Exception
 */
@CrossOrigin
@RestController
@RequestMapping("/wall/update")
@Api("剪力墙编辑类")
public class UpdateController {

    @Autowired
    private transformWallService transformWallService;

    /**
     *剪力墙构件   回显-----查询单条数据
     */
    @RequestMapping(value = "/findOneWall", method = RequestMethod.GET)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "查看单条剪力墙数据", notes = "通过用户名和id查询单个剪力墙数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "Param")
    })
    public ResponseBean findOneWall(@RequestParam(required = false)Integer id,HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            WallTransformInfo wallTransformInfo = transformWallService.findOneWall(id);
            if (!wallTransformInfo.equals(null)) {
                return new ResponseBean(200,"查询成功",wallTransformInfo);
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
    @RequestMapping(value = "/updateWall", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "上传需要修改的参数", notes = "上传转换剪力墙需要的参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "剪力墙png图片", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "prjName", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "seismic", value = "抗震设防烈度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "structure", value = "建筑结构高度", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "scale", value = "比例尺", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    public ResponseBean updateWall( @RequestPart("file") MultipartFile file,
                                   @RequestParam(required = false)String prjName,
                                   @RequestParam(required = false)Integer id,
                                   @RequestParam(required = false)String seismic,
                                   @RequestParam(required = false)String structure,
                                   @RequestParam(required = false)String scale,HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            boolean a = transformWallService.updateWall(file,prjName,id,username,seismic,structure,scale);
            if (a == true) {
                return new ResponseBean(200,"修改成功","成功");
            }else {
                return new ResponseBean(501, "数据上传失败", a);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, prjName+"修改失败", e.getMessage());
        }
    }


}
