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
    int deleteByPrimaryKey(Integer id);

    int insert(WallTransformInfo record);

    int insertSelective(WallTransformInfo record);

    WallTransformInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WallTransformInfo record);

    int update(WallTransformInfo record);

    Page<WallTransformInfo> findWallInfo(@Param("userName") String userName);

    WallTransformInfo getinfo(@Param("username") String username,@Param("prjname") String prjname);

    int insertoutinfo(WallTransformInfo record);
}