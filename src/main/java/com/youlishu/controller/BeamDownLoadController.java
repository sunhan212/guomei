package com.youlishu.controller;


import com.youlishu.bean.ResponseBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *@Description 梁板下载
 *@Author   kd
 *@Date  2022/02/12 20:13
 *Exception
 *version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/download")
public class BeamDownLoadController {

    @Value("${Linux.beamdownload}")
    private String beamdownload;
    /**
     * @function 下载
     * @params
     **/
    @RequestMapping(value = "/beamfile")
    @ResponseBody
    @ApiOperation(value = "下载", notes = "根据文件名下载结果文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    public ResponseBean downloadExcel(HttpServletResponse response,
                                      String fileName,
                                      HttpServletRequest request) {
        try {
            String userName = request.getHeader("username");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            //设置内容类型
            response.setContentType("application/zip;charset=UTF-8");
            //设置要下载的文件的名称

            //获取文件的路径 url从配置文件中获取

            String filePath = (beamdownload + File.separator + fileName);
            FileInputStream input = new FileInputStream(filePath);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[20*2048];
            int len;
            while ((len = input.read(b)) != -1) {
                out.write(b, 0, len);
            }
            response.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
            input.close();
            out.close();
            return new ResponseBean(200, "下载成功", fileName);
        } catch (FileNotFoundException e) {
            return new ResponseBean(1001, "下载文件失败", e.getMessage());
        } catch (IOException e) {
            return new ResponseBean(1001, "下载文件失败", e.getMessage());
        }
    }

}
