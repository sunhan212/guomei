package com.youlishu.service;

import com.alibaba.fastjson.JSON;
import com.youlishu.bean.LoginToken;
import com.youlishu.bean.UserInfo;
import com.youlishu.bean.UserLog;
import com.youlishu.dao.LoginMapper;
import com.youlishu.dao.UserInfoMapper;
import com.youlishu.dao.UserLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yujin
 * @date 2020/12/13 15:17
 **/
@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLogMapper userLogMapper;
    /**
     * 获取token
     * @param username
     * @return
     */
    public LoginToken getToken(String username) {
        return loginMapper.getToken(username);
    }
    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

    /**
     * 添加用户信息
     * @param userInfo
     */
    public void adduserinfo(String userInfo,String token) {
        UserInfo info = JSON.parseObject(userInfo, UserInfo.class);
        info.setToken(token);
        String username = info.getUsername();

        if(userInfoMapper.selectUsername(username) == 0){
//            String token = getSHA256StrJava(username);
//            userInfoBean.setToken(token);
            //todo token还没加进去
            userInfoMapper.insertSelective(info);
        }else {
            userInfoMapper.updatetoken(token,username);
        }
        //登录日志
        UserLog userLog = new UserLog();
        userLog.setUsername(username);
        userLog.setTime(date);
        userLog.setContent("用户："+username+"在"+dateFormat.format(date)+"登录");
        userLogMapper.insert(userLog);
    }
}
