package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BeamTransformMapper {


    BeamTransformInfo getinfo(@Param("username") String username, @Param("id") Integer id);

    int update(BeamTransformInfo transinfo);

    //String findPngFileName(@Param("prjName") String prjName, @Param("username") String username);
}
