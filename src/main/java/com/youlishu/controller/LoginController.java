package com.youlishu.controller;

import com.alibaba.fastjson.JSONObject;
import com.youlishu.bean.LoginToken;
import com.youlishu.bean.ResponseBean;
import com.youlishu.config.UserCenterConfig;
import com.youlishu.service.LoginService;
import com.youlishu.util.HttpClientUtil;
import com.youlishu.util.SignUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yujin
 * @description 登录接口
 * @date 2021/6/24 10:00
 **/
@CrossOrigin
@RestController
@RequestMapping("/login")
@Api("登录类")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserCenterConfig userCenterConfig;
    @ApiOperation("获取token")
    @GetMapping("/getToken")
    private ResponseBean login(String username){
        final LoginToken loginToken = loginService.getToken(username);
        if(StringUtils.isNotBlank(loginToken.getToken())){

            return new ResponseBean(200,"获取token成功",loginToken);
        }
        return new ResponseBean(500,"用户不存在",null);
    }
    @GetMapping("/getusercode")
    @ApiOperation(value = "获取用户信息", notes = "通过UUCS获取的usercode和token传给后台获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_code", value = "用户码", required = true, dataType = "Param"),
            @ApiImplicitParam(name = "token", value = "验证码", required = true, dataType = "Param")
    })
    private ResponseBean getUserCode(String user_code,String token){
        try {
            Map<String, String> map = new HashMap<>();
            //UUCS
            String appKey = "EnGfvA5wus";
            String appSecret = "ifVpv2Zde0NN9oiGk3ONWbJxNySRfWMY";
            Date date = new Date();
            long time = date.getTime() /1000;
            String sign = SignUtil.encode(appKey + " " + appSecret + " " + time);
            map.put("X-Center-Access-Key",appKey);
            map.put("X-Center-Ts",Long.toString(time));
            map.put("X-Center-Sign",sign);

            //获取userinfo
            String userinfo = HttpClientUtil.get(  userCenterConfig.getBasePath()+userCenterConfig.getUserInfo()+ "?user_code="+user_code+"&check_in=false",map);
            JSONObject userInfoJson = JSONObject.parseObject(userinfo);
            //获取用户信息
            String userInfo = userInfoJson.getString("data");
            String flag = userInfoJson.getString("success");

            loginService.adduserinfo(userInfo,token);

            if(StringUtils.isNotBlank(userinfo)){
                if (String.valueOf(true).equals(flag)){
                    return new ResponseBean(200,"获取用户信息成功",userInfoJson);
                }else {
                    return new ResponseBean(501,"获取用户信息失败",userInfoJson);
                }
            }
            return new ResponseBean(502,"获取失败",null);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(500, "获取失败", e.getMessage());
        }
    }

}
