package com.youlishu.service;


import com.github.pagehelper.Page;
import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.dao.BeamFindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeamFindService {

    @Autowired
    private BeamFindMapper beamFindMapper;


    public List findBeamInfo(String userName) {
        List<BeamTransformInfo> list = beamFindMapper.findBeamInfo(userName);
        return list;
    }
}
