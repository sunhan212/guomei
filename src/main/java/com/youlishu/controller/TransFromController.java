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

import javax.xml.xpath.XPath;
import java.io.BufferedReader;
import java.io.Console;
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

//    @Value("${Linux.wallpypath}")
//    private String wallpypath;



    /**
     * 转换算法
     * @param prjname 项目名称
     * @param request header里面的内容
     * @return
     */
    @ApiOperation(value = "进行剪力墙算法转换", notes = "根据项目名称进行转换")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prjname", value = "项目名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "pngFileName", value = "图片名称", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/inwall")
    private ResponseBean transformWall( String prjname ,String pngFileName, HttpServletRequest request){
        //服务器运行python脚本，这一步其实会把所有的文件都转换了，但是数据库不落入信息，用户也看不到
        //String arguments = "python3 /data/java-prj/structGAN/structGAN/StructGAN_p1_wall_20220117.py";
        String pngFileName1 = pngFileName.substring(0,pngFileName.indexOf("."));
        //String path = "C:/Web_system/nginx-1.20.2/html/StructGAN_v0/StructGAN_p1_wall_20220117.py";
        String[] args1 = new String[] { "python", "C:\\Web_system\\nginx-1.20.2\\html\\StructGAN_v0\\StructGAN_p1_wall_20220117.py ",pngFileName1};
        String username = request.getHeader("username");
        Process proc;
        try {
            //proc = Runtime.getRuntime().exec("cmd /c "+ path);
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
                int a = transformWallService.uploadTransformInfo(prjname,username);
                if (a == 1){
                    return new ResponseBean(200,"转换成功","成功");
                }else {
                    return new ResponseBean(500,"数据输入失败","失败");
                }
            }
            else{
                return new ResponseBean(501, "算法转换失败", re+result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(502, "系统转换失败", e.getMessage());
        }
    }
}
