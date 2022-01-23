package com.youlishu.dao;

import com.youlishu.bean.LoginToken;
import com.youlishu.bean.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author TJ
 * @description TODO
 * @date 2020/12/13 15:19
 **/
@Repository
@Mapper
public interface LoginMapper {

    LoginToken getToken(@Param("username") String username);

    UserInfoBean addUserInfo(UserInfoBean userInfoBean);

    int selectUsername(@Param("username") String username);
}
