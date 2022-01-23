package com.youlishu.dao;

import com.youlishu.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**@Author yujin
@Date 2022/1/19 13:58
@Version 1.0
*/
@Mapper
@Repository
public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    int selectUsername(@Param("username") String username);

    int updatetoken(@Param("token") String token,@Param("username")String username);
}