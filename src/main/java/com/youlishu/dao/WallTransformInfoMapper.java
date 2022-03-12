package com.youlishu.dao;

import com.github.pagehelper.Page;
import com.youlishu.bean.WallTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author yujin
 * @Date 2022/1/21 15:16
 * @Version 1.0
 */
@Mapper
@Repository
public interface WallTransformInfoMapper {

    int insertSelective(WallTransformInfo record);

    int update(WallTransformInfo record);

    Page<WallTransformInfo> findWallInfo(@Param("userName") String userName);

    WallTransformInfo getinfo(@Param("username") String username,@Param("id") Integer id);

    WallTransformInfo  findOneWall(@Param("id") Integer id);

    int  updateWall(WallTransformInfo wallTransformInfo);

    int deleteWall(@Param("id") Integer id);
}