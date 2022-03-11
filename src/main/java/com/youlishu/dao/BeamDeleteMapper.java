package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BeamDeleteMapper {
    BeamTransformInfo findOneBeam(@Param("id") Integer id);

    int deleteBeam(@Param("id") Integer id);
}
