package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BeamDeleteMapper {
    BeamTransformInfo findOneBeam(@Param("id") Integer id, @Param("username") String username);

    int deleteBeam(@Param("id") Integer id, @Param("username") String username);
}
