package com.youlishu.controller;


import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamDeleteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *@Description    梁板构建   删除
 *@Author   kd
 *@Date  2022/02/21 22:07
 *Exception
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/delete")
@Api("梁板构件删类除")
public class BeamDeleteController {

    @Autowired
    private BeamDeleteService beamDeleteService;

    /**
     *剪力墙   删除
     */
    @RequestMapping(value = "/deleteBeam", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "删除梁板构建数据", notes = "通过用户名和id删除单个梁板构件数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    public ResponseBean deleteBeam(@RequestParam(required = false)Integer id, HttpServletRequest request){
        try {
            String username = request.getHeader("username");

            boolean a = beamDeleteService.deleteBeam(id,username);
            if (a == true) {
                return new ResponseBean(200,"删除成功","成功");
            }else {
                return new ResponseBean(501, "删除失败", "失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "代码错误", e.getMessage());
        }
    }

}
