package com.youlishu.controller;

import com.youlishu.bean.ResponseBean;
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
 * @Author yujin
 * @Date 2022/1/20 22:10
 * @Version 1.0
 */


@CrossOrigin
@RestController
@RequestMapping("/transform")
@Api("转换类")
public class TransFromController {

    @Autowired
    private com.youlishu.service.transformWallService transformWallService;


//    @Value("${Windows.wallpypath}")
//    private String wallpypath;

    @Value("${Linux.wallpypath}")
    private String wallpypath;



    /**
     * 转换算法
     * @param prjname 项目名称
     * @param request header里面的内容
     * @return
     */
    @ApiOperation(value = "进行剪力墙算法转换", notes = "根据项目名称进行转换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prjname", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/inwall")
    private ResponseBean transformWall( String prjname ,HttpServletRequest request){
        String[] arguments = new String[] {"python", wallpypath};
        String username = request.getHeader("username");
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            in.close();
            int a = transformWallService.uploadTransformInfo(prjname,username);
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
