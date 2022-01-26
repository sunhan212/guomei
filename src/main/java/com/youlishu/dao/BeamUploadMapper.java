package com.youlishu.dao;


import com.youlishu.bean.BeamTransformInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BeamUploadMapper {


    int insertSelective(BeamTransformInfo beamTransformInfo);

    int insertBeamOfWall(BeamTransformInfo beamTransformInfo);
}
