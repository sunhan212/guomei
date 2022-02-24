package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface BeamUpdateMapper {


    BeamTransformInfo findOneBeam(@Param("id") Integer id, @Param("username") String username);

    int updateBeam(BeamTransformInfo beamTransformInfo);
}
