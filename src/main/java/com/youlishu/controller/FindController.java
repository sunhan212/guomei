package com.youlishu.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youlishu.bean.ResponseBean;
import com.youlishu.service.FindService;
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
 * @Author yujin
 * @Date 2022/1/20 18:44
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/find")
@Api("查找类")
public class FindController {

    @Autowired
    private FindService findService;
    /**
     * 查看剪力墙数据
     * @return
     */
    @ApiOperation(value = "查看剪力墙数据列表", notes = "查看当前用户所属的剪力墙数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "pageSize", value = "一页的数量", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "header"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "header")
    })
    @GetMapping("/walldata")
    public ResponseBean wallData(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpServletRequest request){
        //创建JSON map来存放JSON数据传到前台
        JSONObject map = new JSONObject();
        //设置数据库分页查询的范围
        PageHelper.startPage(pageNum, pageSize);
        String userName = request.getHeader("username");
        List list = findService.findWallInfo(userName);
        PageInfo<Menu> pageInfo=new PageInfo<>(list);
        //获取查询总条数
        map.put("count",pageInfo.getTotal());
        map.put("pages",pageInfo.getPages());
        map.put("data",list);

        return new ResponseBean(200,"查询成功",map);
    }
}
