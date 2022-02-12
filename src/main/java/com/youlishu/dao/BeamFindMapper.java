package com.youlishu.dao;


import com.github.pagehelper.Page;
import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BeamFindMapper {


    Page<BeamTransformInfo> findBeamInfo(@Param("userName") String userName);
}
