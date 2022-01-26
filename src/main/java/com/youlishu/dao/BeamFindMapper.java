package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

@Mapper
public interface BeamFindMapper {


    Page<BeamTransformInfo> findBeamInfo(@Param("userName") String userName);
}
