package com.youlishu.dao;

import com.youlishu.bean.UserLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**@Author yujin
@Date 2022/1/20 11:17
@Version 1.0
*/
@Mapper
@Repository
public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);
}