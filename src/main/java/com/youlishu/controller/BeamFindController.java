package com.youlishu.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youlishu.bean.ResponseBean;
import com.youlishu.service.BeamFindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;


/**
 *@Description  梁-板构建查找类
 *@Author   kd
 *@Date  2022/01/25 14:59
 *Exception
 */
@CrossOrigin
@RestController
@RequestMapping("/beam/find")
@Api("梁-查找类")
public class BeamFindController {

    @Autowired
    private BeamFindService beamFindService;
    
    /**
     *@Description  查看梁-板构建设计列表数据
     *@Author   kd
     *@Date  2022/01/25 15:48
     *Exception 
     */
    @ApiOperation(value = "查看梁-板构建数据列表", notes = "查看当前用户所属的梁-板构建数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "pageSize", value = "一页的数量", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/beamdata")
    public ResponseBean beamData(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpServletRequest request){
        //创建JSON map来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        //设置数据库分页查询的范围
        PageHelper.startPage(pageNum, pageSize);
        String userName = request.getHeader("username");
        List list = beamFindService.findBeamInfo(userName);
        PageInfo<Menu> pageInfo=new PageInfo<>(list);
        //获取查询总条数
        map.put("count",pageInfo.getTotal());
        map.put("pages",pageInfo.getPages());
        map.put("data",list);

        return new ResponseBean(200,"查询成功",map);
    }

}
