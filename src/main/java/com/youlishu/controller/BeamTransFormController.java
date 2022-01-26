package com.youlishu.controller;


import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamTransformService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *@Description   梁板转换类
 *@Author   kd
 *@Date  2022/01/26 11:16
 *Exception
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/transform")
@Api("梁-板构建转换类")
public class BeamTransFormController {

    @Autowired
    private BeamTransformService beamTransformService;

      @Value("${Windows.beampypath}")
      private String beampypath;

    //@Value("${Linux.beampypath}")
    //private String beampypath;



    /**
     * 转换算法
     * @param prjname 项目名称
     * @param request header里面的内容
     * @return
     */
    @ApiOperation(value = "进行梁板构建算法转换", notes = "根据项目名称进行转换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prjname", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/inwall")
    private ResponseBean transformWall(String prjname , HttpServletRequest request){
        String[] arguments = new String[] {"python", beampypath};
        String username = request.getHeader("username");
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            in.close();
            int a = beamTransformService.uploadBeamTransformInfo(prjname,username);
            if (a == 1){
                return new ResponseBean(200,"转换成功",null);
            }
            else if (a == 0){
                return new ResponseBean(500, "转换失败", null);
            }
            int re = process.waitFor();

            return new ResponseBean(200,"转换成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "转换失败", null);
        }
    }


}
