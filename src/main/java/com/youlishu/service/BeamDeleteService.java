package com.youlishu.service;


import com.youlishu.bean.BeamTransformInfo;
import com.youlishu.bean.WallTransformInfo;
import com.youlishu.dao.BeamDeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class BeamDeleteService {

    @Autowired
    private BeamDeleteMapper beamDeleteMapper;

    public boolean deleteBeam(Integer id, String username) {
        //查询该数据
        BeamTransformInfo beamTransformInfo = beamDeleteMapper.findOneBeam(id,username);
        //删除服务器上的照片、TXT文件
        /*boolean delete_flag = false;
        File file = new File(beamTransformInfo.getBeamInPngUrl(),beamTransformInfo.getPngFileName());
        if (!file.exists() && !file.isFile() && !file.delete())
            delete_flag = true;
        else
            delete_flag = false;*/
        //删除数据库信息
        if (beamTransformInfo != null){
            int a = beamDeleteMapper.deleteBeam(id,username);
            if (a == 1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
