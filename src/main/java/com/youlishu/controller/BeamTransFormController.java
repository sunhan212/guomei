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

//    @Value("${Linux.beampypath}")
//    private String beampypath;



    /**
     * 转换算法
     * @param prjname 项目名称
     * @param request header里面的内容
     * @return
     */
    @ApiOperation(value = "进行梁板构建算法转换", notes = "根据项目名称进行转换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prjName", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/inbeam")
    private ResponseBean transformBeam(Integer id ,String pngFileName, HttpServletRequest request){
        //服务器运行python脚本，这一步其实会把所有的文件都转换了，但是数据库不落入信息，用户也看不到
//        String arguments = "python3 /data/java-prj/structGAN/structGAN/StructGAN_p2_beam_20220221.py";
        String username = request.getHeader("username");
        //String pngFileName = beamTransformService.findPngFileName(prjName,username);
        String pngFileName1 = pngFileName.substring(0,pngFileName.indexOf("."));
        String[] args1 = new String[] { "python", "C:\\Web_system\\nginx-1.20.2\\html\\StructGAN_v0\\StructGAN_p2_beam_20220222.py ",pngFileName1};

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(args1);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String result = in.readLine();
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //服务器命令执行成功只能是0，其他都是失败
            int re = proc.waitFor();
            if (re == 0){
                int a = beamTransformService.uploadBeamTransformInfo(id,username);
                if (a == 1){
                    return new ResponseBean(200,"转换成功","成功");
                }else {
                    return new ResponseBean(503,"数据输入失败","失败");
                }
            }
            else{
                System.out.println(re);
                return new ResponseBean(501, "算法转换失败", re+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(502, "系统转换失败", e.getMessage());
        }
    }


}
