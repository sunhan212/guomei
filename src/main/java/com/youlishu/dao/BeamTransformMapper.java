package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BeamTransformMapper {


    BeamTransformInfo getinfo(@Param("username") String username, @Param("prjname") String prjname);

    int update(BeamTransformInfo transinfo);
}
